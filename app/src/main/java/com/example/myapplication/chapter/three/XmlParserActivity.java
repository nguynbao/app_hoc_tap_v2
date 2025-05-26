package com.example.myapplication.chapter.three;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;
import androidx.activity.EdgeToEdge;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.example.myapplication.R;

import java.io.IOException;

public class XmlParserActivity extends AppCompatActivity {
    private TextView txt_xml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xml_parser);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        parseXml();
    }
    private void parseXml() {
        try {
            XmlPullParser parser = getResources().getXml(R.xml.users); // users.xml phải nằm trong res/xml
            StringBuilder sb = new StringBuilder();
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("user")) {
                    String name = parser.getAttributeValue(null, "name");
                    Log.d("XML", "Tên người dùng: " + name);
                    sb.append("Tên người dùng: ").append(name).append("\n");
                }
            }
            txt_xml = findViewById(R.id.txt_xml_result);
            txt_xml.setText(sb.toString());
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
            Log.e("XML", "Lỗi khi phân tích XML: " + e.getMessage());
        }
    }
}
