package com.example.calculadoraimc

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
            calcularIMC()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calcularIMC() {
        val nome = binding.nome.text.toString()
        val idade = binding.idade.text.toString().toIntOrNull() ?: 0
        val peso = binding.peso.text.toString().toDoubleOrNull() ?: 0.0
        val altura = binding.altura.text.toString().toDoubleOrNull() ?: 1.0

        val imc = if (altura != 0.0) peso / (altura * altura) else 0.0

        var tabelaIMC = when {
            imc < 16 -> "magreza grave"
            imc in 16.0..16.9 -> "magreza moderada"
            imc in 17.0..18.4 -> "magreza leve"
            imc in 18.5..24.9 -> "peso ideal"
            imc in 25.0..29.9 -> "sobrepeso"
            imc in 30.0..34.9 -> "obesidade grau I"
            imc in 35.0..39.9 -> "obesidade grau II ou severa"
            imc >= 40 -> "obesidade grau III ou mórbida"
            else -> "valor inválido"
        }

        val resultado = binding.resultado
        if (imc != 0.00) {
            resultado.text =
                "Olá, $nome! Seu IMC atual é de %.2f. Sua classificação atual é: $tabelaIMC".format(
                    imc
                )

        } else {
            resultado.text = "Por favor, insira dados válidos."

        }

    }

//    resultados menores que 16 — magreza grave;
//    resultados entre 16 e 16,9 — magreza moderada;
//    resultados entre 17 e 18,5 — magreza leve;
//    resultados entre 18,6 e 24,9 — peso ideal;
//    resultados entre 25 e 29,9 — sobrepeso;
//    resultados entre 30 e 34,9 — obesidade grau I;
//    resultados entre 35 e 39,9 — obesidade grau II ou severa;
//    resultados maiores do que 40 — obesidade grau III ou mórbida.
}
