package com.kawser.cprf;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ProjectProposalSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_proposal_success);

        Button saveButton = findViewById(R.id.pp_success_save_button);

        String Course_code = getIntent().getStringExtra("course_code");
        String Course_title = getIntent().getStringExtra("course_title");
        String Project_title = getIntent().getStringExtra("project_title");
        String Team_member1_name = getIntent().getStringExtra("team_member1_name");
        String Team_member1_id = getIntent().getStringExtra("team_member1_id");
        String Team_member2_name = getIntent().getStringExtra("team_member2_name");
        String Team_member2_id = getIntent().getStringExtra("team_member2_id");
        String Team_member3_name = getIntent().getStringExtra("team_member3_name");
        String Team_member3_id = getIntent().getStringExtra("team_member3_id");
        String Team_member4_name = getIntent().getStringExtra("team_member4_name");
        String Team_member4_id = getIntent().getStringExtra("team_member4_id");
        String Team_batch = getIntent().getStringExtra("team_batch");
        String Team_session = getIntent().getStringExtra("team_session");
        String File_name = getIntent().getStringExtra("file_name");



        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save the PDF file to the device
                try {
                    createPdf(Course_code, Course_title, Project_title, Team_member1_name, Team_member1_id, Team_member2_name, Team_member2_id, Team_member3_name, Team_member3_id, Team_member4_name, Team_member4_id, Team_batch, Team_session, File_name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void createPdf(String Course_code, String Course_title, String Project_title, String Team_member1_name, String Team_member1_id, String Team_member2_name, String Team_member2_id, String Team_member3_name, String Team_member3_id, String Team_member4_name, String Team_member4_id, String Team_batch, String Team_session, String FileName) throws IOException {
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

                // times new roman font
                PdfFont timesNewRomanFont = PdfFontFactory.createFont("res/font/timesnewroman.ttf");

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
                C_code.setFont(timesNewRomanFont).setFontSize(18).setTextAlignment(TextAlignment.CENTER);

                Text C_title_title = new Text("Course Title: ").setBold();
                Text C_title_val = new Text(Course_title);
                Paragraph C_title = new Paragraph();
                C_title.add(C_title_title);
                C_title.add(C_title_val);
                C_title.setFont(timesNewRomanFont).setFontSize(18).setTextAlignment(TextAlignment.CENTER);


                Text PP_title = new Text("Project Title: ").setBold();
                Text PP_title_val = new Text(Project_title);
                Paragraph Ppos_title = new Paragraph();
                Ppos_title.add(PP_title);
                Ppos_title.add(PP_title_val);
                Ppos_title.setFont(timesNewRomanFont).setFontSize(20).setTextAlignment(TextAlignment.CENTER);


                document.add(BauetName);
                document.add(address);
                document.add(image);
                document.add(deptName);
                document.add(Assignment);
                document.add(C_code);
                document.add(C_title);
                document.close();
                pdfDocument.close();
                writer.close();
//                Toast.makeText(AssignmentSuccessActivity.this, "Front Page Successfully Saved On\n➡️" + pdfPath + "⬅️", Toast.LENGTH_LONG).show();
            }
        });



    }
}