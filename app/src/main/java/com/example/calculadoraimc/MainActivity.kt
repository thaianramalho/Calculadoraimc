package com.example.calculadoraimc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.calcular.setOnClickListener {
            val resultado = calcularIMC()
            binding.resultado.text = resultado
        }

        binding.exportar.setOnClickListener {
            val resultado = calcularIMC()  // Calcula o IMC e obtém o resultado
            val intent = Intent(this, ExportActivity::class.java)
            intent.putExtra("IMC_RESULT", resultado)  // Envia o resultado do IMC para a ExportActivity
            startActivity(intent)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun calcularIMC(): String {
        val peso = binding.peso.text.toString().toDoubleOrNull()
        val altura = binding.altura.text.toString().toDoubleOrNull()

        if (peso != null && altura != null) {
            val imc = peso / (altura * altura)

            return String.format("IMC: %.2f", imc)
        } else {
            return "Por favor, insira valores válidos para peso e altura."
        }
    }


}
