package com.example.lectordecodigos

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FotoActivity : AppCompatActivity() {

    //Instancias
    private lateinit var foto: ImageView
    private lateinit var btnTomar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_foto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Asociar con instancia
        foto = findViewById(R.id.imgFoto)
        btnTomar = findViewById(R.id.btnTomar)
//Mëtodos
        btnTomar.setOnClickListener {
//Intancia para abrir la cámara
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//Lo que sucede cuando la cámara regresa un resultado
            responseLauncher.launch(intent)
        }

    }//onCreate


    //Variable que se ejecuta una vez que tome la foto
    private val responseLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ activityResult ->
            if(activityResult.resultCode == RESULT_OK){
                Toast.makeText(this, "Fotografía tomada!!!",
                    Toast.LENGTH_SHORT).show()
                val extras = activityResult.data!!.extras
                val imgBitmap = extras!!["data"] as Bitmap?
                foto.setImageBitmap(imgBitmap)
            } else {
                Toast.makeText(this, "Proceso cancelado",
                    Toast.LENGTH_SHORT).show()
            }
        }//responseLauncher


}//Class