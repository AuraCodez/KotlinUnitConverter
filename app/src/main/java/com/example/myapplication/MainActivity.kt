import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Converter
import com.example.myapplication.MorseCodeConverter
import com.example.myapplication.R
import com.example.myapplication.UnitConverter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStream = assets.open("apiKey.txt")
        val apiKey = inputStream.bufferedReader().use {
            it.readText()
        }

        val currencyBtn = findViewById<Button>(R.id.currencyButton)
        currencyBtn.setOnClickListener {
            val intent = Intent(this, Converter::class.java)
            intent.putExtra("API_KEY", apiKey)
            startActivity(intent)
        }

        val unitConverterBtn = findViewById<Button>(R.id.unitButton)
        unitConverterBtn.setOnClickListener {
            val intent = Intent(this, UnitConverter::class.java)
            startActivity(intent)
        }

        val morseCodeBtn = findViewById<Button>(R.id.morseButton)
        morseCodeBtn.setOnClickListener {
            val intent = Intent(this, MorseCodeConverter::class.java)
            startActivity(intent)
        }



        // The api instance could be used here to call the API methods
    }
}
