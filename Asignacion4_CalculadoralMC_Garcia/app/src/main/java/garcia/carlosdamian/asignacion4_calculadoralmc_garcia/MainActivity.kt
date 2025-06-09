package garcia.carlosdamian.asignacion4_calculadoralmc_garcia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {


    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var imcTextView: TextView
    private lateinit var rangeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        weightEditText = findViewById(R.id.weight)
        heightEditText = findViewById(R.id.height)
        calculateButton = findViewById(R.id.calculateButton)
        imcTextView = findViewById(R.id.imc)
        rangeTextView = findViewById(R.id.range)

        // Configurar el evento onClickListener para el botón
        calculateButton.setOnClickListener {
            calculateIMC()
        }
    }

    /**
     * Calcula el IMC y lo despliega en la interfaz.
     */
    private fun calculateIMC() {
        val weightStr = weightEditText.text.toString()
        val heightStr = heightEditText.text.toString()


        if (weightStr.isNotEmpty() && heightStr.isNotEmpty()) {
            val weight = weightStr.toFloat()
            val height = heightStr.toFloat()


            if (height > 0) {

                val imc = weight / (height * height)


                imcTextView.text = String.format("%.2f", imc)

                // Determinar y mostrar la categoría y el color correspondiente
                displayIMCCategory(imc)
            }
        }
    }

    /**
     * Despliega la categoría del IMC y cambia el color de fondo del TextView
     */
    private fun displayIMCCategory(imc: Float) {
        val category: String
        val color: Int

        // Asignar categoría y color según la tabla de IMC
        when {
            imc < 18.5 -> {
                category = "Bajo peso"
                color = R.color.colorYellow
            }
            imc < 25 -> {
                category = "Normal"
                color = R.color.colorGreen
            }
            imc < 30 -> {
                category = "Sobrepeso"
                color = R.color.colorGreenish
            }
            imc < 35 -> {
                category = "Obesidad grado 1"
                color = R.color.colorOrange
            }
            imc < 40 -> {
                category = "Obesidad grado 2"
                color = R.color.colorRed
            }
            else -> {
                category = "Obesidad grado 3"
                color = R.color.colorBrown
            }
        }

        rangeTextView.text = category

        rangeTextView.setBackgroundResource(color)
    }
}