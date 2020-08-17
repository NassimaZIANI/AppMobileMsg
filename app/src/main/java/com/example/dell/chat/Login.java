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
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * L'interface d'authentification
 */
public class Login extends AppCompatActivity {

    MaterialEditText email, password;
    Button btn_login;

    FirebaseAuth auth;
    TextView forgot_password;
    TextView compte;

    /**
     * Création de l'interface d'authentification
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Authentification");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    auth = FirebaseAuth.getInstance();

    email = findViewById(R.id.email);
    password = findViewById(R.id.password);
    btn_login = findViewById(R.id.btn_login);
    forgot_password = findViewById(R.id.forgot_password);
    compte = findViewById(R.id.compte);

    // si l'utilisateur a oublier son mot de passe : rediriger vers l'interface de reinitialisation du mot de passe
    forgot_password.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Login.this, ResetPasswordActivity.class));
        }
    });

    // si l'utilisateur souhaite créer un compte : rediriger vers l'interface d'inscription
    compte.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Login.this, Signin.class));
        }
    });

    // si l'utilisateur veut se connecter à son compte
    btn_login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            // récuperer l'email et le mot de passe saisie dans les champs
            String txt_email = email.getText().toString();
            String txt_password = password.getText().toString();

            // verifier si les champs sont pas vides
            if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                Toast.makeText(Login.this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();

            // verifier dans la BDD si le mail et le mot de passe existent et correspondent au compte de l'utilisateur
            }else {
                auth.signInWithEmailAndPassword(txt_email, txt_password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                // si la verification est faite avec succées : l'utilisateur est redirigé vers l'interface pricipale de la messagerie
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(Login.this, StartActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();

                                // sinon un message d'erreur est affiché à l'utilisateur
                                }else{
                                    Toast.makeText(Login.this, "echec d'authentification", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    });
    }
}
