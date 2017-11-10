package com.example.khutsomatlala.hackaton_user11;

import android.app.DatePickerDialog;

import android.app.TimePickerDialog;

import android.content.Intent;

import android.os.Build;

import android.os.Bundle;

import android.support.annotation.RequiresApi;

import android.support.v7.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;

import android.widget.Button;

import android.widget.DatePicker;

import android.widget.EditText;
import android.widget.ImageView;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import android.widget.TimePicker;

import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Admin on 9/15/2017.
 */

public class book_new extends AppCompatActivity {

    //time and date memebers

    private int mHoursIn, mHoursOut, mMinsOut, mMinsIn, mDay, mMonth, mYear, mDiffMins;

    private float mTotal = 0;

    String duration;

    Boolean TimeIn = false, TimeOut = false, Calculate = false, dateEntered = false, one = false, more = false;

    private static int HOUR_PRICE = 20;

/*    private TextView mDateDisplay, mPrice;

    private Button mPickDate;

    EditText mNumberHours;*/

    static final int DATE_DIALOG_ID = 0;

    String pic, date1, name, pricee, mTimeIn, mTimeOut, mDate, mEmail,mUsername;

    ImageView BookPic;


    TextView placeName, mPrice, tv_date, txtTotalPrice;
    String dayStamp = new SimpleDateFormat("yyyy.MM.dd").format(new Date());

    //Time and date picker
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();

    Calendar dateTime = Calendar.getInstance();

    private TextView text;

    private TextView textout;

    private Button btn_date;

    private Button btn_time;

    private Button btn_time_out;

    //validate

    int date = 0;

    int month = 0;


    //Number of people
    RadioButton MorePeople, onePerson;
    RadioGroup radioGroup;
    EditText PeopleNumber;
    String   numberOfPeople;


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference   mAvailabilityReference;

    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //  setContentView(R.layout.activity_book);
        setContentView(R.layout.activity_book);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mAvailabilityReference = mFirebaseDatabase.getReference().child("availability");
        Intent i = getIntent();

        pic = i.getStringExtra("pic");

        name = i.getStringExtra("name");

        pricee = i.getStringExtra("price");

        mEmail = i.getStringExtra("email");
        mUsername = i.getStringExtra("mUsername");




        mPrice = (TextView) findViewById(R.id.txtPrice);

        BookPic = (ImageView) findViewById(R.id.BookPic);

        placeName = (TextView) findViewById(R.id.txtPlace_name);

        txtTotalPrice = (TextView) findViewById(R.id.txtTotalPrice);

        tv_date = (TextView) findViewById(R.id.date);

        MorePeople = (RadioButton) findViewById(R.id.rb_MorePeople);
        onePerson = (RadioButton) findViewById(R.id.rb_onePerson);
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        PeopleNumber = (EditText) findViewById(R.id.edtPeopleNumber);

        mPrice.setText("R" + pricee + "/h");


        Glide.with(this)
                .load(pic)
                //  .override(300, 200)
                .centerCrop()
                .into(BookPic);

        placeName.setText(name);

        text = (TextView) findViewById(R.id.txt_TextDateTime);

        textout = (TextView) findViewById(R.id.txt_TextDateTimeOut);

        btn_date = (Button) findViewById(R.id.btn_datePicker);

        btn_time = (Button) findViewById(R.id.btn_timePicker);

        btn_time_out = (Button) findViewById(R.id.btn_timePickerOut);

        btn_date.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                updateDate();

            }

        });

        btn_time.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                updateTime();

            }

        });

        btn_time_out.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                updateTimeOut();

            }

        });

    }

    //Date

    private void updateDate() {

        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR), dateTime.get(Calendar.MONTH), dateTime.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void updateTime() {

        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();

    }

    private void updateTimeOut() {

        new TimePickerDialog(this, out, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();

    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            month = Calendar.getInstance().get(Calendar.MONTH);

            if (month < monthOfYear || month == monthOfYear) {

                dateTime.set(Calendar.YEAR, year);

                dateTime.set(Calendar.MONTH, monthOfYear);

                dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                tv_date.setText(dayOfMonth + " - " + monthOfYear + "-" + year);

                mDay = dayOfMonth;

                mMonth = monthOfYear;

                mYear = year;

                mDate = String.valueOf(mDay + " - " + mMonth + " - " + mYear);


            } else {

                Toast.makeText(book_new.this, " Incorrect month", Toast.LENGTH_SHORT).show();

            }

            if (monthOfYear < dayOfMonth || month == dayOfMonth) {

                dateTime.set(Calendar.YEAR, year);

                dateTime.set(Calendar.MONTH, monthOfYear);

                dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                dateEntered = true;
            } else {

                Toast.makeText(book_new.this, "Enter valid date", Toast.LENGTH_SHORT).show();

            }

        }

    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {

        @Override

//time in

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            TimeIn = true;

            dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);

            dateTime.set(Calendar.MINUTE, minute);

            date = hourOfDay;

            text.setText(hourOfDay + ":" + minute);

            mTimeIn = String.valueOf(hourOfDay + ":" + minute);

            mHoursIn = hourOfDay;

            mMinsIn = minute;

            String form;

            if (hourOfDay >= 12) {

                mHoursIn = hourOfDay - 12;

                form = "PM";

            } else {

                if (hourOfDay == 0) {

                    mHoursIn = 12;

                }

                form = "AM";

            }


            if (mMinsIn < 10) {
                text.setText(mHoursIn + ":0" + mMinsIn + " " + form);
            } else {
                text.setText(mHoursIn + ":" + mMinsIn + " " + form);
            }


        }

    };

    //time out

    TimePickerDialog.OnTimeSetListener out = new TimePickerDialog.OnTimeSetListener() {

        @Override

        public void onTimeSet(TimePicker timePicker, int hours, int mins) {

            dateTime.set(Calendar.HOUR_OF_DAY, hours);

            dateTime.set(Calendar.MINUTE, mins);

            TimeOut = true;

            mHoursOut = hours;

            mMinsOut = mins;


            if (mHoursIn < mHoursOut) {


                String form;

                if (mHoursOut >= 12) {

                    mHoursOut = mHoursOut - 12;

                    form = "PM";

                } else {

                    if (mHoursOut == 0) {

                        mHoursOut = 12;

                    }

                    form = "AM";

                }

                mTimeOut = String.valueOf(hours + ":" + mins);

                if (mMinsOut < 10) {
                    textout.setText(mHoursOut + ":0" + mMinsOut + " " + form);
                } else {
                    textout.setText(mHoursOut + ":" + mMinsOut + " " + form);
                }


            } else {
                Toast.makeText(book_new.this, "in hour(s)  more than out hours", Toast.LENGTH_SHORT).show();

            }
        }

    };


    public void email(View view) {

    /*    if (TimeOut && TimeOut & dateEntered & (one || more)) {

            mBookingInfor = "Date - " + mDate + "\n"
                    + "Time in - " + mTimeIn + "\n"
                    + "Time out - " + mTimeOut + "\n"
                    + "Number of people - " + numberOfPeople + "\n"
                    + "Price - R" + mTotal + "0";

            Intent email = new Intent(Intent.ACTION_SEND);

            email.putExtra(Intent.EXTRA_EMAIL, new String[]{mEmail});

            email.putExtra(Intent.EXTRA_SUBJECT, " Space booking  for " + name);

            email.putExtra(Intent.EXTRA_TEXT, mBookingInfor);

            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "choose an Email client"));

        } else

        {

            Toast.makeText(this, "Fill all the  fields", Toast.LENGTH_SHORT).show();

        }



*/


        mAvailabilityReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mAvailabilityReference.child(name).child("book_day").setValue(dayStamp);
                mAvailabilityReference.child(name).child("Bookings").child("user_id").setValue(mUsername);
                mAvailabilityReference.child(name).child("Bookings").child("start_time").setValue(mHoursIn);
                mAvailabilityReference.child(name).child("Bookings").child("end_time").setValue(mHoursOut);
                String time = ""+ mHoursIn + mHoursOut;
                mAvailabilityReference.child(name).child("hours_booked").setValue(time);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void NumPeople(View view) {

        int selectedId = radioGroup.getCheckedRadioButtonId();


        switch (selectedId) {

            case R.id.rb_MorePeople:
                txtTotalPrice.setText("R0.00");

                mTotal = 0;
                more = true;
                if (dateEntered) {
                    if (TimeIn && TimeOut) {

                        mDiffMins = mMinsIn + mMinsOut;

                        duration = String.valueOf(mHoursOut - mHoursIn);

                        if (mDiffMins >= 60) {

                            mTotal = (mHoursOut - mHoursIn) + 1;

                        } else {

                            mTotal = (mHoursOut - mHoursIn);

                        }

                        mTotal = mTotal * Integer.parseInt(pricee);

                          numberOfPeople = PeopleNumber.getText().toString();


                        if (!TextUtils.isEmpty(numberOfPeople)) {
                            txtTotalPrice.setText("R" + Math.abs( mTotal) * Integer.parseInt(numberOfPeople) + "0");
                        } else {
                            Toast.makeText(this, "enter number of people", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(this, "Enter time in and out", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Select a date", Toast.LENGTH_SHORT).show();
                }
                
                break;

            case R.id.rb_onePerson:
                one = true;
                mTotal = 0;

                numberOfPeople = "1";

                if (TimeIn && TimeOut) {

                    mDiffMins = mMinsIn + mMinsOut;

                    duration = String.valueOf(mHoursOut - mHoursIn);

                    if (mDiffMins >= 60) {

                        mTotal = (mHoursOut - mHoursIn) + 1;

                    } else {

                        mTotal = (mHoursOut - mHoursIn);

                    }

                    mTotal = mTotal * Integer.parseInt(pricee);

                    txtTotalPrice.setText("R" +Math.abs(mTotal) + "0");
                } else {
                    Toast.makeText(this, "Enter time in and out", Toast.LENGTH_SHORT).show();
                }


                break;

        }

    }

}

