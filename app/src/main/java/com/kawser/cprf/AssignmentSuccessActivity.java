package com.kawser.cprf;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;



public class AssignmentSuccessActivity extends AppCompatActivity {
    // Times new roman font
    PdfFont timesNewRomanFont = PdfFontFactory.createFont("res/font/timesnewroman.ttf");

    public AssignmentSuccessActivity() throws IOException {
    }


    public Text ordinal(int n) {
        final String s;
        if (11 >= n && n <= 13) {
            s = "th";
        } else if (n % 10 == 1) {
            s = "st";
        } else if (n % 10 == 2) {
            s = "nd";
        } else if (n % 10 == 3) {
            s = "rd";
        } else {
            s = "th";
        }
        Text st = new Text(s).setFont(timesNewRomanFont).setFontSize(8);
        st.setTextRise(6);
        return st;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_success);


        Button saveButton = findViewById(R.id.assignment_success_save_button);



        // Get the PDF content from the previous page
        String Name = getIntent().getStringExtra("name");
        String Id = getIntent().getStringExtra("id");
        String Batch = getIntent().getStringExtra("batch");
        String Year = getIntent().getStringExtra("year");
        String Semester = getIntent().getStringExtra("semester");
        String Session = getIntent().getStringExtra("session");
        String Course_code = getIntent().getStringExtra("course_code");
        String Course_title = getIntent().getStringExtra("course_title");
        String Teacher1 = getIntent().getStringExtra("teacher1");
        String Teacher1_pos = getIntent().getStringExtra("teacher1_pos");
        String Teacher2 = getIntent().getStringExtra("teacher2");
        String Teacher2_pos = getIntent().getStringExtra("teacher2_pos");
        String Teacher3 = getIntent().getStringExtra("teacher3");
        String Teacher3_pos = getIntent().getStringExtra("teacher3_pos");
        String FileName = getIntent().getStringExtra("file_name");




//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
//                Uri uri = Uri.parse(pdfPath);
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setDataAndType(uri,"application/pdf");
////                intent.setData(Uri.parse(pdfPath));
//                startActivity(intent);
//
//            }
//        });





        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save the PDF file to the device
                try {
                    createPdf(FileName, Name, Id, Batch, Year, Semester, Session, Course_code, Course_title, Teacher1, Teacher1_pos, Teacher2, Teacher2_pos, Teacher3, Teacher3_pos);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void createPdf(String FileName, String Name, String Id, String Batch, String Year, String Semester, String Session, String Course_code, String Course_title, String Teacher1, String Teacher1_pos, String Teacher2, String Teacher2_pos, String Teacher3, String Teacher3_pos) throws IOException {
                String pdfPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
//            Text f_name = new Text(FileName + ".pdf");

                // pdf file name
                File file = new File(pdfPath,(FileName + ".pdf"));
                OutputStream outputStream = new FileOutputStream(file);

                PdfWriter writer = new PdfWriter(file);
                PdfDocument pdfDocument = new PdfDocument(writer);
                Document document = new Document(pdfDocument);

                pdfDocument.setDefaultPageSize(PageSize.A4);
                document.setMargins(1,1,1,1);

                Text t1 = new Text("\n\n\nBANGLADESH ARMY UNIVERSITY OF ENGINEERING &\nTECHNOLOGY (BAUET)").setFont(timesNewRomanFont);
                Paragraph BauetName = new Paragraph(t1).setBold().setFontSize(18).setTextAlignment(TextAlignment.CENTER);


                Paragraph address = new Paragraph("Qadirabad Cantonment, Natore-6431").setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);

                Drawable d = getDrawable(R.drawable.bauetlogo1);
                Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bitmapData = stream.toByteArray();

                // logo
                ImageData imageData = ImageDataFactory.create(bitmapData);
                Image image = new Image(imageData);
                image.scaleAbsolute(150f,  150f).setFixedPosition(230f, 527f);

                Paragraph deptName = new Paragraph("\n\n\n\n\n\nDEPARTMENT OF COMPUTER SCIENCE AND ENGINEERING").setFont(timesNewRomanFont).setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER);
                Paragraph Assignment = new Paragraph("\nAssignment").setFont(timesNewRomanFont).setBold().setFontSize(24).setTextAlignment(TextAlignment.CENTER);

                Text C_code_title = new Text("\nCourse Code: ").setBold();
                Text C_code_val = new Text(Course_code);
                Paragraph C_code = new Paragraph();
                C_code.add(C_code_title);
                C_code.add(C_code_val);
                C_code.setFont(timesNewRomanFont).setFontSize(16).setTextAlignment(TextAlignment.CENTER);

                Text C_title_title = new Text("Course Title: ").setBold();
                Text C_title_val = new Text(Course_title);
                Paragraph C_title = new Paragraph();
                C_title.add(C_title_title);
                C_title.add(C_title_val);
                C_title.setFont(timesNewRomanFont).setFontSize(16).setTextAlignment(TextAlignment.CENTER);


                float[] width = {2000f,2000f};
                Table table = new Table(width).setFixedPosition(60, 80, 640);

                table.addCell(new Cell().add(new Paragraph("Submitted By: ").setFont(timesNewRomanFont).setBold().setFontSize(16)).setBorder(Border.NO_BORDER));
                table.addCell(new Cell().add(new Paragraph("Submitted To: ").setFont(timesNewRomanFont).setBold().setFontSize(16)).setBorder(Border.NO_BORDER));

                table.addCell(new Cell().add(new Paragraph("Name: " + Name).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
                table.addCell(new Cell().add(new Paragraph(Teacher1).setBold().setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));

                table.addCell(new Cell().add(new Paragraph("ID: " + Id).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
                table.addCell(new Cell().add(new Paragraph(Teacher1_pos).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));

//            table.addCell(new Cell().add(new Paragraph("ID: " + Id)).setBorder(Border.NO_BORDER));
//            table.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));

                table.addCell(new Cell().add(new Paragraph("Batch: " + Batch).add(ordinal(Integer.parseInt(Batch))).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
                table.addCell(new Cell().add(new Paragraph(Teacher2).setBold().setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));

//            table.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));
//            table.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));

                table.addCell(new Cell().add(new Paragraph("Year: " + Year).add(ordinal(Integer.parseInt(Year))).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
                table.addCell(new Cell().add(new Paragraph(Teacher2_pos).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));

                table.addCell(new Cell().add(new Paragraph("Semester: " + Semester).add(ordinal(Integer.parseInt(Semester))).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
                table.addCell(new Cell().add(new Paragraph(Teacher3).setFont(timesNewRomanFont).setBold().setFontSize(14)).setBorder(Border.NO_BORDER));

                table.addCell(new Cell().add(new Paragraph("Session: " + Session).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));
                table.addCell(new Cell().add(new Paragraph(Teacher3_pos).setFont(timesNewRomanFont).setFontSize(14)).setBorder(Border.NO_BORDER));

            /*
            to less bold .setFontColor(new Color(0, 0, 0, 0.5))
             */
                document.add(BauetName);
                document.add(address);
                document.add(image);
                document.add(deptName);
                document.add(Assignment);
                document.add(C_code);
                document.add(C_title);
                document.add(table);
                document.close();
                pdfDocument.close();
                writer.close();
                Toast.makeText(AssignmentSuccessActivity.this, "Front Page Successfully Saved On\n➡️" + pdfPath + "⬅️", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void buttonOpenFile(View view){
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            intent = new Intent(Intent.ACTION_VIEW, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
        }
        intent.setType("*/*");
        this.startActivity(intent);
    }

}