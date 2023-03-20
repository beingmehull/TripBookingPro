package com.sitinoldae.tripbookingpro

import android.annotation.SuppressLint
import android.app.Dialog
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import com.noowenz.customdatetimepicker.CustomDateTimePicker
import lib.android.imagepicker.ImagePicker
import java.util.*

private lateinit var EtPackageName:EditText
private lateinit var EtStops:EditText
private lateinit var EtPrice:EditText
private lateinit var ArrivalBtn:Button
private lateinit var ImageSelectionBtn:Button
private lateinit var DepartureBtn:Button
private lateinit var SavePackageBtn:Button
private lateinit var SpinnerPackageType:Spinner
private lateinit var imagePicker: ImagePicker
private lateinit var ivSelectedImage:ImageView


class AddTrips : AppCompatActivity(),ImagePicker.OnImageSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_trips)

        imagePicker = ImagePicker(this, BuildConfig.APPLICATION_ID)
        imagePicker.setImageSelectedListener(this)

        EtPackageName=findViewById(R.id.et_package_name)
        EtStops=findViewById(R.id.et_package_stops)
        EtPrice=findViewById(R.id.et_package_price)
        ArrivalBtn=findViewById(R.id.arrival_time_btn)
        ImageSelectionBtn=findViewById(R.id.image_selection_btn)
        DepartureBtn=findViewById(R.id.departure_time_btn)
        SpinnerPackageType=findViewById(R.id.spinner_package_type)
        ivSelectedImage=findViewById(R.id.iv_selected_image)
        SavePackageBtn=findViewById(R.id.save_package_btn)

        ImageSelectionBtn.setOnClickListener {
            imagePicker.takePhotoFromGallery()
        }

        SavePackageBtn.setOnClickListener {
            if(EtPackageName.text.isNullOrEmpty()){
                EtPackageName.error = "please enter package name"
            }else
            if(EtStops.text.isNullOrEmpty()){
                EtStops.error = "please enter stops name (multiline)"
            }else
            if(EtPrice.text.isNullOrEmpty()){
                EtPrice.error = "please enter Price of the package"
            }

        }
        ArrivalBtn.setOnClickListener {
            CustomDateTimePicker(this, object : CustomDateTimePicker.ICustomDateTimeListener {
                @SuppressLint("BinaryOperationInTimber")
                override fun onSet(
                    dialog: Dialog,
                    calendarSelected: Calendar,
                    dateSelected: Date,
                    year: Int,
                    monthFullName: String,
                    monthShortName: String,
                    monthNumber: Int,
                    day: Int,
                    weekDayFullName: String,
                    weekDayShortName: String,
                    hour24: Int,
                    hour12: Int,
                    min: Int,
                    sec: Int,
                    AM_PM: String
                ) {
                    //Get any time of date and time data here and process further...
                }

                override fun onCancel() {
                }
            }).apply {
                set24HourFormat(false)//24hr format is off
                setMaxMinDisplayDate(
                    minDate = Calendar.getInstance().apply { add(Calendar.MINUTE, 5) }.timeInMillis,//min date is 5 min after current time
                    maxDate = Calendar.getInstance().apply { add(Calendar.YEAR, 1) }.timeInMillis//max date is next 1 year 
                )
                setMaxMinDisplayedTime(5)//min time is 5 min after current time
                setDate(Calendar.getInstance())//date and time will show in dialog is current time and date. We can change this according to our need
                showDialog()
            }
        }
        DepartureBtn.setOnClickListener {
            CustomDateTimePicker(this, object : CustomDateTimePicker.ICustomDateTimeListener {
                @SuppressLint("BinaryOperationInTimber")
                override fun onSet(
                    dialog: Dialog,
                    calendarSelected: Calendar,
                    dateSelected: Date,
                    year: Int,
                    monthFullName: String,
                    monthShortName: String,
                    monthNumber: Int,
                    day: Int,
                    weekDayFullName: String,
                    weekDayShortName: String,
                    hour24: Int,
                    hour12: Int,
                    min: Int,
                    sec: Int,
                    AM_PM: String
                ) {
                    //Get any time of date and time data here and process further...
                }

                override fun onCancel() {
                }
            }).apply {
                set24HourFormat(false)//24hr format is off
                setMaxMinDisplayDate(
                    minDate = Calendar.getInstance().apply { add(Calendar.MINUTE, 5) }.timeInMillis,//min date is 5 min after current time
                    maxDate = Calendar.getInstance().apply { add(Calendar.YEAR, 1) }.timeInMillis//max date is next 1 year 
                )
                setMaxMinDisplayedTime(5)//min time is 5 min after current time
                setDate(Calendar.getInstance())//date and time will show in dialog is current time and date. We can change this according to our need
                showDialog()
            }
        }
    }

    override fun onImageSelectFailure() {
        Toast.makeText(applicationContext,"Image Selection Failed !!",Toast.LENGTH_SHORT).show()
    }

    override fun onImageSelectSuccess(imagePath: String) {
        ivSelectedImage.setImageURI(Uri.parse(imagePath))
        Toast.makeText(applicationContext,"Image Selection Success !!\n$imagePath",Toast.LENGTH_SHORT).show()
    }
}