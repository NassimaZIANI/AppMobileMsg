package com.example.dell.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * L'interface à l'ouverture de l'application
 */
public class MainActivity extends AppCompatActivity {

    Button login, signin;

    FirebaseUser firebaseUser;

    /**
     * Verifier si un utilisateur a laisser sa session ouverte lors de l'ouverture de l'application
     */
    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // check if user is null
        if (firebaseUser != null) {
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * Création de l'interface d'accueil
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView t = (TextView) findViewById(R.id.Bienvenue);
        login = (Button) findViewById(R.id.login);
        signin = (Button) findViewById(R.id.signin);

        // Si l'utilisateur veut se connecter, il clique sur le bouton login et il sera rediriger vers l'interface d'authentification
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

        // Si l'utilisateur veut s'inscrire, il clique sur le bouton signin et il sera rediriger vers l'interface d'inscription
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Signin.class));
            }
        });
    }
}
