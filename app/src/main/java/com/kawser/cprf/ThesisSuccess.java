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
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ThesisSuccess extends AppCompatActivity {
    // Times new roman font
    PdfFont timesNewRomanFont = PdfFontFactory.createFont("res/font/timesnewroman.ttf");

    public ThesisSuccess() throws IOException {
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
        setContentView(R.layout.activity_thesis_success);

        Button saveButton = findViewById(R.id.th_success_save_button);

        String Course_code = getIntent().getStringExtra("course_code");
        String Course_title = getIntent().getStringExtra("course_title");
        String Thesis_title = getIntent().getStringExtra("thesis_title");
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
                    createPdf(Course_code, Course_title, Thesis_title, Team_member1_name, Team_member1_id, Team_member2_name, Team_member2_id, Team_member3_name, Team_member3_id, Team_member4_name, Team_member4_id, Team_batch, Team_session, File_name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private void createPdf(String Course_code, String Course_title, String Thesis_title, String Team_member1_name, String Team_member1_id, String Team_member2_name, String Team_member2_id, String Team_member3_name, String Team_member3_id, String Team_member4_name, String Team_member4_id, String Team_batch, String Team_session, String FileName) throws IOException {
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

                // times new roman italic font
                PdfFont timesNewRomanFontItalic = PdfFontFactory.createFont("res/font/timesnewromanitalic.ttf");

                Paragraph knowledge_tech =  new Paragraph("\n\n\nKnowledge & Technology").setFont(timesNewRomanFontItalic).setFontSize(12).setTextAlignment(TextAlignment.CENTER);


                Text t1 = new Text("BANGLADESH ARMY UNIVERSITY OF ENGINEERING &\nTECHNOLOGY (BAUET)").setFont(timesNewRomanFont);
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
                image.scaleAbsolute(150f,  150f).setFixedPosition(230f, 490f);

                Paragraph deptName = new Paragraph("DEPARTMENT OF COMPUTER SCIENCE AND ENGINEERING").setFont(timesNewRomanFont).setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER);
//                Paragraph Assignment = new Paragraph("\nAssignment").setFont(timesNewRomanFont).setBold().setFontSize(24).setTextAlignment(TextAlignment.CENTER);


                Text C_code_title = new Text("\n\n\n\n\n\nCourse Code: ").setBold();
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

                Text PP_title = new Text("Thesis Title: ").setBold();
                Text PP_title_val = new Text(Thesis_title);
                Paragraph Ppos_title = new Paragraph();
                Ppos_title.add(PP_title);
                Ppos_title.add(PP_title_val);
                Ppos_title.setFont(timesNewRomanFont).setFontSize(20).setTextAlignment(TextAlignment.CENTER);

                Paragraph submitBy = new Paragraph("\nSubmit By:").setFont(timesNewRomanFont).setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER);

                // Member1 name
                Text mem1 = new Text("Name: ").setBold();
                Text mem1_name = new Text(Team_member1_name);
                Paragraph member1_name = new Paragraph();
                member1_name.add(mem1).add(mem1_name);
                member1_name.setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
                member1_name.setMultipliedLeading(0.7f);
                // Member1 id
                Text mem1_id = new Text("ID: ").setBold();
                Text mem1_id_val = new Text(Team_member1_id);
                Paragraph member1_id = new Paragraph();
                member1_id.add(mem1_id).add(mem1_id_val);
                member1_id.setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
                member1_id.setMultipliedLeading(0.7f);

                // Member2 name
                Text mem2 = new Text("Name: ").setBold();
                Text mem2_name = new Text(Team_member2_name);
                Paragraph member2_name = new Paragraph();
                member2_name.add(mem2).add(mem2_name);
                member2_name.setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
                member2_name.setMultipliedLeading(0.7f);
                // Member2 id
                Text mem2_id = new Text("ID: ").setBold();
                Text mem2_id_val = new Text(Team_member2_id);
                Paragraph member2_id = new Paragraph();
                member2_id.add(mem2_id).add(mem2_id_val);
                member2_id.setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
                member2_id.setMultipliedLeading(0.7f);

                // Member3 name
                Text mem3 = new Text("Name: ").setBold();
                Text mem3_name = new Text(Team_member3_name);
                Paragraph member3_name = new Paragraph();
                member3_name.add(mem3).add(mem3_name);
                member3_name.setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
                member3_name.setMultipliedLeading(0.7f);
                // Member3 id
                Text mem3_id = new Text("ID: ").setBold();
                Text mem3_id_val = new Text(Team_member3_id);
                Paragraph member3_id = new Paragraph();
                member3_id.add(mem3_id).add(mem3_id_val);
                member3_id.setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
                member3_id.setMultipliedLeading(0.7f);

                // Member4 name
                Text mem4 = new Text("Name: ").setBold();
                Text mem4_name = new Text(Team_member4_name);
                Paragraph member4_name = new Paragraph();
                member4_name.add(mem4).add(mem4_name);
                member4_name.setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
                member4_name.setMultipliedLeading(0.7f);
                // Member4 id
                Text mem4_id = new Text("ID: ").setBold();
                Text mem4_id_val = new Text(Team_member4_id);
                Paragraph member4_id = new Paragraph();
                member4_id.add(mem4_id).add(mem4_id_val);
                member4_id.setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
                member4_id.setMultipliedLeading(0.7f);

                // Batch
                Text mem_batch = new Text("Batch: ").setBold();
                Text mem_batch_val = new Text(Team_batch);
                Paragraph batch = new Paragraph();
                batch.add(mem_batch).add(mem_batch_val).add(ordinal(Integer.parseInt(Team_batch)));
                batch.setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
                batch.setMultipliedLeading(0.7f);

                //Session
                Text mem_session = new Text("Session: ").setBold();
                Text mem_session_val = new Text(Team_session);
                Paragraph session = new Paragraph();
                session.add(mem_session).add(mem_session_val);
                session.setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
                session.setMultipliedLeading(0.7f);

                // Department of Computer Science and Engineering (CSE)
                Paragraph dept = new Paragraph("\nDepartment of Computer Science and Engineering (CSE)").setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);
                Paragraph bottomBauet = new Paragraph("Bangladesh Army University of Engineering & Technology (BAUET)").setFont(timesNewRomanFont).setFontSize(14).setTextAlignment(TextAlignment.CENTER);

                document.add(knowledge_tech);
                document.add(BauetName);
                document.add(address);
                document.add(deptName);
                document.add(image);
                document.add(C_code);
                document.add(C_title);
                document.add(Ppos_title);
                document.add(submitBy);
                document.add(member1_name);
                document.add(member1_id);
                if (Team_member2_name.length() != 0) {
                    document.add(member2_name);
                    document.add(member2_id);
                }
                if (Team_member3_name.length() != 0) {
                    document.add(member3_name);
                    document.add(member3_id);
                }
                if (Team_member4_name.length() != 0) {
                    document.add(member4_name);
                    document.add(member4_id);
                }
                document.add(batch);
                document.add(session);
                document.add(dept);
                document.add(bottomBauet);


                document.close();
                pdfDocument.close();
                writer.close();
                Toast.makeText(ThesisSuccess.this, "Thesis Report Front Page Successfully Saved On\n➡️" + pdfPath, Toast.LENGTH_LONG).show();
            }
        });
    }
    public void Thesis_buttonOpenFile(View view){
        Intent intent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            intent = new Intent(Intent.ACTION_VIEW, MediaStore.Downloads.EXTERNAL_CONTENT_URI);
        }
        intent.setType("*/*");
        this.startActivity(intent);
    }
}