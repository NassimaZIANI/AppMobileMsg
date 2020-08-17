package com.example.dell.chat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

/**
 * L'interface d'inscription
 */
public class Signin extends AppCompatActivity {

    MaterialEditText username, email, password;
    Button btn_signin;
    TextView compte;

    FirebaseAuth auth;
    DatabaseReference reference;

    /**
     * Création de l'interface d'inscription
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inscription");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_signin = findViewById(R.id.btn_signin);
        compte = findViewById(R.id.compte);

        auth = FirebaseAuth.getInstance();

        compte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signin.this, Login.class));
            }
        });

        // verification lors du click sur le bouton signin
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // recupération de l'username, email et mot de passe en string
                String txt_username = username.getText().toString();
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                // verifier si l'utilisateur a saisie tous les champs
                if (TextUtils.isEmpty(txt_username) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(Signin.this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
                }else
                    // verifier si le mot de passe a plus de 6 caracteres
                    if (txt_password.length() < 6){
                    Toast.makeText(Signin.this, "Mot de passe doit contenir au moins 6 caractères", Toast.LENGTH_SHORT).show();
                }else{
                    // si la verification s'est bien passé, enregistrer le nouveau utilisateur
                    register(txt_username, txt_email, txt_password);
                }
            }
        });

    }

    /**
     * la fonction register permet d'ajouter l'username, l'email et le mot de passe à la base de donnée
     * @param username : username saisie par le nouveau utilisateur
     * @param email : email saisie par le nouveau utilisateur
     * @param password : mot de passe saisie par le nouveau utilisateur
     */
    private void register(final String username, String email, String password){

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // si les champs sont bien saisies : ajoutées a la base de donnée
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            // crée un hashmap pour ajouter l'id, username, une image et le status dans la base de donnée
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username", username);
                            hashMap.put("imageURL", "default");
                            hashMap.put("status", "offline");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    // si l'inscription est faite avec succés : rediriger l'utilisateur vers l'interface de connexion
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(Signin.this, Login.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }

                                }
                            });

                        // si il y a un problem dans l'inscription afficher l'erreur à l'utilisateur
                        }else {
                            Toast.makeText(Signin.this, "Vous pouvez pas vous enregistrer avec cettte email ou mot de passe", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
