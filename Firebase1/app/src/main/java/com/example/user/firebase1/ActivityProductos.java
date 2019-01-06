package com.example.user.firebase1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.firebase1.model.Productos;
import com.example.user.firebase1.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityProductos extends AppCompatActivity {

    EditText nombre, categoria, descripcion, precio;
    Button añadir, atras, borrar;
    Spinner spin;

    DatabaseReference bbdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        nombre = (EditText) findViewById(R.id.nombre);
        categoria = (EditText) findViewById(R.id.categoria);
        descripcion = (EditText) findViewById(R.id.descripcion);
        precio = (EditText) findViewById(R.id.precio);
        spin = (Spinner) findViewById(R.id.spinner);
        borrar = (Button) findViewById(R.id.borrar);
        atras = (Button) findViewById(R.id.cerrar);
        añadir = (Button) findViewById(R.id.anyadir);


        bbdd = FirebaseDatabase.getInstance().getReference("usuarios");

        bbdd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayAdapter<String> adaptador;
                ArrayList<String> listado = new ArrayList<String>();

                for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                    Usuario user = datasnapshot.getValue(Usuario.class);


                    String nomuser = user.getNomuser();

                    listado.add(nomuser);


                }
                adaptador = new ArrayAdapter<String>(ActivityProductos.this,android.R.layout.simple_list_item_1,listado);
                spin.setAdapter(adaptador);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        añadir.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String nom = nombre.getText().toString();
                String cat = categoria.getText().toString();
                String des = descripcion.getText().toString();
                String pre = precio.getText().toString();
                String nomuser = spin.getSelectedItem().toString();

                if(!TextUtils.isEmpty(nom)){
                    Productos p = new Productos(nom,des,cat,pre,nomuser);

                    DatabaseReference bbdd2 = FirebaseDatabase.getInstance().getReference("productos");

                    String clave = bbdd2.push().getKey();

                    bbdd2.child(clave).setValue(p);

                    Toast.makeText( ActivityProductos.this, "Producto añadido", Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(ActivityProductos.this, "Debes de introducir un nombre", Toast.LENGTH_LONG).show();
                }

            }
        });

        /*borrar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String nom = nombre.getText().toString();

                if(!TextUtils.isEmpty(nom)){
                    Query q=bbdd.orderByChild(getString(R.string.n_n)).equalTo(nom);

                    q.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                                String clave=datasnapshot.getKey();
                                DatabaseReference ref = bbdd.child(clave);

                                ref.removeValue();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    Toast.makeText(ActivityProductos.this, "El Producto "+nom+" se ha borrado con éxito", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ActivityProductos.this, "Debes de introducir un nombre", Toast.LENGTH_LONG).show();
                }

            }
        });*/

        atras.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });

    }
}
