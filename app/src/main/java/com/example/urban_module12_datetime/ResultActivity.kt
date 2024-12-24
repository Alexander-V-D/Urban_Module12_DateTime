package com.example.urban_module12_datetime

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.Locale

class ResultActivity : AppCompatActivity() {

    private lateinit var resultPictureIV: ImageView
    private lateinit var nameTV: TextView
    private lateinit var surnameTV: TextView
    private lateinit var ageTV: TextView
    private lateinit var timeUntilBirthdayTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultPictureIV = findViewById(R.id.resultPictureIV)
        nameTV = findViewById(R.id.nameTV)
        surnameTV = findViewById(R.id.surnameTV)
        ageTV = findViewById(R.id.ageTV)
        timeUntilBirthdayTV = findViewById(R.id.timeUntilBirthdayTV)

        val person = intent.getParcelableExtra<Person>("person")
        if (person != null) {
            if (person.picture != null) resultPictureIV.setImageURI(Uri.parse(person.picture))
            if (person.name != null) nameTV.text = person.name
            if (person.surname != null) surnameTV.text = person.surname
            if (person.birthDay != null) {
                val (day, month, year) = person.birthDay.split(".")
                val calendar = Calendar.getInstance()
                calendar.set(year.toInt(), month.toInt() - 1, day.toInt())
//                ageTV.text = String.format(
//                    Locale.getDefault(), "Возраст: " +
//                            (System.currentTimeMillis() - calendar.timeInMillis) / 31_536_000_000
//                )
                ageTV.text = getString(
                    R.string.current_age,
                    ((System.currentTimeMillis() - calendar.timeInMillis) / 31_536_000_000)
                        .toString()
                )
                calendar.set(
                    Calendar.YEAR,
                    ((System.currentTimeMillis() / 31_536_000_000) + 1971).toInt()
                )
                val timeBeforeBirthday = calendar.timeInMillis - System.currentTimeMillis()
                calendar.timeInMillis = timeBeforeBirthday
//                timeUntilBirthdayTV.text = "До дня рождения:" +
//                        " ${(calendar.get(Calendar.MONTH))} месяц(ев) и " +
//                        "${calendar.get(Calendar.DAY_OF_MONTH)} дн(ень/ей)"
                timeUntilBirthdayTV.text = getString(R.string.time_before_birthday,
                    calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            }
        }
    }
}