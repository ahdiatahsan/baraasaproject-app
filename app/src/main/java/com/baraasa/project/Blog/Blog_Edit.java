package com.baraasa.project.Blog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baraasa.project.R;

import jp.wasabeef.richeditor.RichEditor;

public class Blog_Edit extends AppCompatActivity {

    private Toolbar toolbar;
    private RichEditor mEditor;
    private TextView mPreview;
    private EditText Judul, Thumbnail;
    private Button upload;
    private int nameIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_edit);
        getSupportActionBar().hide();
        toolbar = (Toolbar) findViewById(R.id.toolbareditblog);
        toolbar.setTitle("Ubah Blog");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.inflateMenu(R.menu.ceklis);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_ceklis) {
                    Toast.makeText(Blog_Edit.this, "Tambah", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

//        mEditor = (RichEditor) findViewById(R.id.editeditor);
//        mEditor.setEditorHeight(200);
//        mEditor.setEditorFontSize(14);
//        mEditor.setEditorFontColor(Color.BLACK);
//        //mEditor.setEditorBackgroundColor(Color.BLUE);
//        //mEditor.setBackgroundColor(Color.BLUE);
//        //mEditor.setBackgroundResource(R.drawable.bg);
//        mEditor.setPadding(10, 10, 10, 10);
//        //mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
//        mEditor.setPlaceholder("Masukkan Isi Konten");
//        //mEditor.setInputEnabled(false);
//
//        Judul = findViewById(R.id.editjudulBlog);
//        Thumbnail = findViewById(R.id.editthumbnailBlog);
////        upload = findViewById(R.id.btnthumbnail);
//        Thumbnail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(pickPhoto, 0);//one can be replaced with any action code
//
//            }
//        });
//        mPreview = (TextView) findViewById(R.id.editpreview);
//        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
//            @Override
//            public void onTextChange(String text) {
//
//                String result = "<p>" + text.replaceAll("(<b>)+", "<strong>").replaceAll("(</b>)+", "</strong>").
//                        replaceAll("(<br>)+", "</p><p>").replaceAll("(<i>)+", "<em>").replaceAll("(</i>)+", "</em>").
//                        replaceAll("(<u>)+", "<span style=\"text-decoration:underline;\">").replaceAll("(</u>)+", "</span>").
//                        replaceAll("(<strike>)+", "<span style=\"text-decoration:line-through;\">").replaceAll("(</strike>)+", "</span>") + "</p>";
//                // String[] test = result.split("<p class=\"mb-8\">");
////                String tem = "";
////                for(int i=0; i<test.length;i++){
////                    if(test[i].split("[\\s\\W\\S\\.]+[<li>]")[0]=="<ol>"){
////                        tem += "<p class=\"mb-8\">" + test[i]+"</p>";
////                    } else {
////                        tem += test[i];
////                    }
////
////                }
//                mPreview.setText(result);
//            }
//        });
//
//        findViewById(R.id.editaction_undo).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.undo();
//            }
//        });
//
//        findViewById(R.id.editaction_redo).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.redo();
//            }
//        });
//
//        findViewById(R.id.editaction_bold).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setBold();
//            }
//        });
//
//        findViewById(R.id.editaction_italic).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setItalic();
//            }
//        });
//
//        findViewById(R.id.editaction_subscript).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setSubscript();
//            }
//        });
//
//        findViewById(R.id.editaction_superscript).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setSuperscript();
//            }
//        });
//
//        findViewById(R.id.editaction_strikethrough).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setStrikeThrough();
//            }
//        });
//
//        findViewById(R.id.editaction_underline).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setUnderline();
//            }
//        });
//
//        findViewById(R.id.editaction_heading1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(1);
//            }
//        });
//
//        findViewById(R.id.editaction_heading2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(2);
//            }
//        });
//
//        findViewById(R.id.editaction_heading3).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(3);
//            }
//        });
//
//        findViewById(R.id.editaction_heading4).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(4);
//            }
//        });
//
//        findViewById(R.id.editaction_heading5).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(5);
//            }
//        });
//
//        findViewById(R.id.editaction_heading6).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setHeading(6);
//            }
//        });
//
//        findViewById(R.id.editaction_align_left).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setAlignLeft();
//            }
//        });
//
//        findViewById(R.id.editaction_align_center).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setAlignCenter();
//            }
//        });
//
//        findViewById(R.id.editaction_align_right).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setAlignRight();
//            }
//        });
//
//        findViewById(R.id.editaction_insert_bullets).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setBullets();
//            }
//        });
//
//        findViewById(R.id.editaction_insert_numbers).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEditor.setNumbers();
//            }
//        });
//
//        findViewById(R.id.editaction_insert_image).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                // Creating alert Dialog with one Button
////                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Blog_Tambah.this);
////                // Setting Dialog Title
////                alertDialog.setTitle("Masukkan Link");
////                LinearLayout layout = new LinearLayout(Blog_Tambah.this);
////                layout.setOrientation(LinearLayout.VERTICAL);
////                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
////                        LinearLayout.LayoutParams.MATCH_PARENT,
////                        LinearLayout.LayoutParams.WRAP_CONTENT);
////                lp.setMargins(55,10,55,10);
////                // Setting Dialog Message
////                final EditText link = new EditText(Blog_Tambah.this);
////                link.setLayoutParams(lp);
////                link.setHint("Link");
////                link.setFocusable(false);
////                link.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                        startActivityForResult(pickPhoto, 0);//one can be replaced with any action code
////                    }
////                });
////                layout.addView(link);
////
////                final EditText teks = new EditText(Blog_Tambah.this);
////                teks.setLayoutParams(lp);
////                teks.setHint("Teks");
////                layout.addView(teks);
////                alertDialog.setView(layout);
////                // Setting Positive "Yes" Button
////                alertDialog.setPositiveButton("YES",
////                        new DialogInterface.OnClickListener() {
////                            public void onClick(DialogInterface dialog, int which) {
////                                // Write your code here to execute after dialog
////                                mEditor.insertImage(link.getText().toString(), teks.getText().toString());
////                            }
////                        });
////                // Setting Negative "NO" Button
////                alertDialog.setNegativeButton("NO",
////                        new DialogInterface.OnClickListener() {
////                            public void onClick(DialogInterface dialog, int which) {
////                                // Write your code here to execute after dialog
////                                dialog.cancel();
////                            }
////                        });
////
////                // closed
////
////                // Showing Alert Message
////                alertDialog.show();
//
//                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code
//            }
//
////                mEditor.insertImage("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg",
////                        "dachshund", 320);
//        });
//
//        findViewById(R.id.editaction_insert_link).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Creating alert Dialog with one Button
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Blog_Edit.this);
//                // Setting Dialog Title
//                alertDialog.setTitle("Masukkan Link");
//                LinearLayout layout = new LinearLayout(Blog_Edit.this);
//                layout.setOrientation(LinearLayout.VERTICAL);
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.MATCH_PARENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT);
//                lp.setMargins(55, 10, 55, 10);
//                // Setting Dialog Message
//                final EditText link = new EditText(Blog_Edit.this);
//                link.setLayoutParams(lp);
//                link.setHint("Link");
//                layout.addView(link); // Notice this is an add method
//                final EditText teks = new EditText(Blog_Edit.this);
//                teks.setLayoutParams(lp);
//                teks.setHint("Teks");
//                layout.addView(teks);
//                alertDialog.setView(layout);
//                // Setting Positive "Yes" Button
//                alertDialog.setPositiveButton("YES",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Write your code here to execute after dialog
//
//                                mEditor.insertLink(link.getText().toString(), teks.getText().toString());
//                            }
//                        });
//                // Setting Negative "NO" Button
//                alertDialog.setNegativeButton("NO",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Write your code here to execute after dialog
//                                dialog.cancel();
//                            }
//                        });
//
//                // closed
//
//                // Showing Alert Message
//                alertDialog.show();
////                mEditor.insertLink("https://github.com/wasabeef", "wasabeef");
//            }
//        });
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != RESULT_CANCELED) {
//            switch (requestCode) {
//                case 0:
//                    if (resultCode == RESULT_OK && data != null) {
//                        Uri selectedImage = data.getData();
//                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                        if (selectedImage != null) {
//                            Cursor cursor = getContentResolver().query(selectedImage,
//                                    filePathColumn, null, null, null);
//                            if (cursor != null) {
//                                nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//                                cursor.moveToFirst();
//                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                                String picturePath = cursor.getString(columnIndex);
//                                File file = new File(picturePath);
//                                String filename = file.getName();
//                                Thumbnail.setText(filename);
//                                cursor.close();
//                            }
//                        }
//
//                    }
//                    break;
//                case 1:
//                    if (resultCode == RESULT_OK && data != null) {
//                        Uri selectedImage = data.getData();
//                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                        if (selectedImage != null) {
//                            Cursor cursor = getContentResolver().query(selectedImage,
//                                    filePathColumn, null, null, null);
//                            if (cursor != null) {
//                                nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
//                                cursor.moveToFirst();
//                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                                String picturePath = cursor.getString(columnIndex);
//                                File file = new File(picturePath);
//                                String filename = file.getName();
//                                mEditor.insertImage(filename, "dachshund", 320);
//                                cursor.close();
//                            }
//                        }
//
//                    }
//                    break;
//            }
//        }
    }
}