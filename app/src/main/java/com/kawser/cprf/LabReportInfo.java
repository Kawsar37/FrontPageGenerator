package com.kawser.cprf;


import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.Calendar;

public class LabReportInfo extends AppCompatActivity {
    EditText file_name;
    EditText name;
    EditText id;
    EditText batch;
    EditText year;
    EditText semester;
    EditText session;
    EditText course_code;
    EditText course_title;
    EditText lab_no;
    EditText exp_date;
    EditText sub_date;
    EditText exp_name;
    EditText teacher1;
    EditText teacher1_pos;
    EditText teacher2;
    EditText teacher2_pos;
    EditText teacher3;
    EditText teacher3_pos;
    Button submit;


    public static final int REQUEST_STORAGE = 101;

//    public String ordinal(int n) {
//        final String s;
//        if (11 <= n && n <= 13) {
//            s = "th";
//        } else if (n % 10 == 1) {
//            s = "st";
//        } else if (n % 10 == 2) {
//            s = "nd";
//        } else if (n % 10 == 3) {
//            s = "rd";
//        } else {
//            s = "th";
//        }
//        return s;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_report_info);
        int permissionGranted = PackageManager.PERMISSION_GRANTED;
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        // for experiment date
        exp_date = (EditText)findViewById(R.id.edit_text_ex_date);
        Calendar calendar = Calendar.getInstance();
        exp_date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(LabReportInfo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        exp_date.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        // for submission date
        sub_date = (EditText)findViewById(R.id.edit_text_sub_date);
        Calendar scalendar = Calendar.getInstance();
        sub_date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int year = scalendar.get(Calendar.YEAR);
                int month = scalendar.get(Calendar.MONTH);
                int day = scalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(LabReportInfo.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        sub_date.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        file_name = (EditText) findViewById(R.id.edit_text_file_name);
        name = (EditText) findViewById(R.id.edit_text_name);
        id = (EditText) findViewById(R.id.edit_text_id);
        batch = (EditText) findViewById(R.id.edit_text_batch);
        year = (EditText) findViewById(R.id.edit_text_year);
        semester = (EditText) findViewById(R.id.edit_text_semester);
        session = (EditText) findViewById(R.id.edit_text_session);
        course_code = (EditText) findViewById(R.id.edit_text_course_code);
        course_title = (EditText) findViewById(R.id.edit_text_course_title);
        lab_no = (EditText) findViewById(R.id.edit_text_lab_no);
        exp_name = (EditText) findViewById(R.id.edit_text_exp_name);
        teacher1 = (EditText) findViewById(R.id.edit_text_submitted_teacher1);
        teacher1_pos = (EditText) findViewById(R.id.edit_text_pos_teacher1);
        teacher2 = (EditText) findViewById(R.id.edit_text_submitted_teacher2);
        teacher2_pos = (EditText) findViewById(R.id.edit_text_pos_teacher2);
        teacher3 = (EditText) findViewById(R.id.edit_text_submitted_teacher3);
        teacher3_pos = (EditText) findViewById(R.id.edit_text_pos_teacher3);
        submit = (Button) findViewById(R.id.submit_button_lab_report);


        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (file_name.getText().toString().length() == 0 ||
                        name.getText().toString().length() == 0 ||
                        id.getText().toString().length() == 0 ||
                        batch.getText().toString().length() == 0 ||
                        year.getText().toString().length() == 0 ||
                        semester.getText().toString().length() == 0 ||
                        course_code.getText().toString().length() == 0 ||
                        course_title.getText().toString().length() == 0 ||
                        teacher1.getText().toString().length() == 0 ||
                        teacher1_pos.getText().toString().length() == 0){

                        Toast.makeText(LabReportInfo.this, "Some Info Missing", Toast.LENGTH_LONG).show();
                } else {
//                    String FileName = file_name.getText().toString();
//                    String Name = name.getText().toString();
//                    String Id = id.getText().toString();
//                    String Batch = batch.getText().toString();
//                    String Year = year.getText().toString();
//                    String Semester = semester.getText().toString();
//                    String Course_code = course_code.getText().toString();
//                    String Course_title = course_title.getText().toString();
//                    String Lab_no = lab_no.getText().toString();
//                    String Exp_date = exp_date.getText().toString();
//                    String Sub_date = sub_date.getText().toString();
//                    String Exp_name = exp_name.getText().toString();
//                    String Teacher1 = teacher1.getText().toString();
//                    String Teacher1_pos = teacher1_pos.getText().toString();
//                    String Teacher2 = teacher2.getText().toString();
//                    String Teacher2_pos = teacher2_pos.getText().toString();
//                    String Teacher3 = teacher3.getText().toString();
//                    String Teacher3_pos = teacher3_pos.getText().toString();



//                    try {
//                        createPdf(FileName, Name, Id, Batch, Year, Semester, Course_code, Course_title, Lab_no, Exp_date, Sub_date, Exp_name, Teacher1, Teacher1_pos, Teacher2, Teacher2_pos, Teacher3, Teacher3_pos);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }


                    // charges
                    Intent intent = new Intent(LabReportInfo.this, LabReportSuccessActivity.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("id", id.getText().toString());
                    intent.putExtra("batch", batch.getText().toString());
                    intent.putExtra("year", year.getText().toString());
                    intent.putExtra("semester", semester.getText().toString());
                    intent.putExtra("session", session.getText().toString());
                    intent.putExtra("course_code", course_code.getText().toString());
                    intent.putExtra("course_title", course_title.getText().toString());
                    intent.putExtra("lab_no", lab_no.getText().toString());
                    intent.putExtra("exp_date", exp_date.getText().toString());
                    intent.putExtra("sub_date", sub_date.getText().toString());
                    intent.putExtra("exp_name", exp_name.getText().toString());
                    intent.putExtra("teacher1", teacher1.getText().toString());
                    intent.putExtra("teacher1_pos", teacher1_pos.getText().toString());
                    intent.putExtra("teacher2", teacher2.getText().toString());
                    intent.putExtra("teacher2_pos", teacher2_pos.getText().toString());
                    intent.putExtra("teacher3", teacher3.getText().toString());
                    intent.putExtra("teacher3_pos", teacher3_pos.getText().toString());
                    intent.putExtra("file_name", file_name.getText().toString());

                    startActivity(intent);
                }
            }
        });
    }


//        private void createPdf(String FileName, String Name, String Id, String Batch, String Year, String Semester, String Course_code, String Course_title, String Lab_no, String Exp_date, String Sub_date, String Exp_name, String Teacher1, String Teacher1_pos, String Teacher2, String Teacher2_pos, String Teacher3, String Teacher3_pos) throws IOException {
//            String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
////            Text f_name = new Text(FileName + ".pdf");
//            File file = new File(pdfPath,(FileName + ".pdf"));
//            OutputStream outputStream = new FileOutputStream(file);
//
//            PdfWriter writer = new PdfWriter(file);
//            PdfDocument pdfDocument = new PdfDocument(writer);
//            Document document = new Document(pdfDocument);
//
//            pdfDocument.setDefaultPageSize(PageSize.A4);
//            document.setMargins(1,1,1,1);
//
//            // times new roman font
//            PdfFont timesNewRomanFont = PdfFontFactory.createFont("res/font/timesnewroman.ttf");
//
//            Text t1 = new Text("\n\n\nBANGLADESH ARMY UNIVERSITY OF ENGINEERING &\nTECHNOLOGY (BAUET)").setFont(timesNewRomanFont);
//            Paragraph BauetName = new Paragraph(t1).setBold().setFontSize(18).setTextAlignment(TextAlignment.CENTER);
//
//
//            Paragraph address = new Paragraph("Qadirabad Cantonment, Natore-6431").setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
//
//            Drawable d = getDrawable(R.drawable.bauetlogo1);
//            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] bitmapData = stream.toByteArray();
//
//            // logo
//            ImageData imageData = ImageDataFactory.create(bitmapData);
//            Image image = new Image(imageData);
//            image.scaleAbsolute(150f,  150f).setFixedPosition(230f, 527f);
//
//            Paragraph deptName = new Paragraph("\n\n\n\n\n\nDEPARTMENT OF COMPUTER SCIENCE AND ENGINEERING").setFont(timesNewRomanFont).setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER);
//            Paragraph lab_no = new Paragraph("Lab Report: " + Lab_no).setFont(timesNewRomanFont).setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER);
//
//            Text C_code_title = new Text("Course Code: ").setBold();
//            Text C_code_val = new Text(Course_code);
//            Paragraph C_code = new Paragraph();
//            C_code.add(C_code_title);
//            C_code.add(C_code_val);
//            C_code.setFont(timesNewRomanFont).setFontSize(16).setTextAlignment(TextAlignment.CENTER);
//
//            Text C_title_title = new Text("Course Title: ").setBold();
//            Text C_title_val = new Text(Course_title);
//            Paragraph C_title = new Paragraph();
//            C_title.add(C_title_title);
//            C_title.add(C_title_val);
//            C_title.setFont(timesNewRomanFont).setFontSize(16).setTextAlignment(TextAlignment.CENTER);
//
//            Text exp_name_title = new Text("Experiment Name: ").setBold();
//            Text exp_name_val = new Text(Exp_name);
//            Paragraph Ex_name = new Paragraph();
//            Ex_name.add(exp_name_title);
//            Ex_name.add(exp_name_val);
//            Ex_name.setFont(timesNewRomanFont).setFontSize(16).setTextAlignment(TextAlignment.CENTER);
//
//            Text Exp_date_title = new Text("Experiment Date: ").setBold();
//            Text Exp_date_val = new Text(Exp_date);
//            Paragraph ex_date = new Paragraph();
//            ex_date.add(Exp_date_title);
//            ex_date.add(Exp_date_val);
//            ex_date.setFont(timesNewRomanFont).setFontSize(16).setTextAlignment(TextAlignment.CENTER);
//
//            Text sub_date_title = new Text("Submission Date: ").setBold();
//            Text sub_date_val = new Text(Sub_date);
//            Paragraph sub_date = new Paragraph();
//            sub_date.add(sub_date_title);
//            sub_date.add(sub_date_val);
//            sub_date.setFont(timesNewRomanFont).setFontSize(16).setTextAlignment(TextAlignment.CENTER);
//
//
//            float[] width = {2000f,2000f};
//            Table table = new Table(width).setFixedPosition(60, 80, 640);
//
//            table.addCell(new Cell().add(new Paragraph("Submitted By: ").setFont(timesNewRomanFont).setBold().setFontSize(16)).setBorder(Border.NO_BORDER));
//            table.addCell(new Cell().add(new Paragraph("Submitted To: ").setFont(timesNewRomanFont).setBold().setFontSize(16)).setBorder(Border.NO_BORDER));
//
//            table.addCell(new Cell().add(new Paragraph("Name: " + Name).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
//            table.addCell(new Cell().add(new Paragraph(Teacher1).setBold().setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
//
//            table.addCell(new Cell().add(new Paragraph("ID: " + Id).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
//            table.addCell(new Cell().add(new Paragraph(Teacher1_pos).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
//
////            table.addCell(new Cell().add(new Paragraph("ID: " + Id)).setBorder(Border.NO_BORDER));
////            table.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
//
//            table.addCell(new Cell().add(new Paragraph("Batch: " + Batch).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
//            table.addCell(new Cell().add(new Paragraph(Teacher2).setBold().setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
//
////            table.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
////            table.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
//
//            table.addCell(new Cell().add(new Paragraph("Year: " + Year).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
//            table.addCell(new Cell().add(new Paragraph(Teacher2_pos).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
//
//            table.addCell(new Cell().add(new Paragraph("Semester: " + Semester).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
//            table.addCell(new Cell().add(new Paragraph(Teacher3).setFont(timesNewRomanFont).setBold().setFontSize(14)).setBorder(Border.NO_BORDER));
//
//            table.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
//            table.addCell(new Cell().add(new Paragraph(Teacher3_pos).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
//
//            /*
//            to less bold .setFontColor(new Color(0, 0, 0, 0.5))
//             */
//            document.add(BauetName);
//            document.add(address);
//            document.add(image);
//            document.add(deptName);
//            document.add(lab_no);
//            document.add(C_code);
//            document.add(C_title);
//            if (Exp_name.length() != 0) {
//                document.add(Ex_name);
//            }
//            document.add(ex_date);
//            document.add(sub_date);
//            document.add(table);
//            document.close();
//            Toast.makeText(this, "Front Page Created Successfully", Toast.LENGTH_LONG).show();
//        }
    }