package ly.moi.forceerp;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLogin();
    }

    private void showLogin() {

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(40, 40, 40, 40);
        layout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        TextView title = new TextView(this);
        title.setText("منظومة إدارة القوة العمومية");
        title.setTextSize(24);
        title.setTextColor(Color.DKGRAY);
        title.setGravity(Gravity.CENTER);

        TextView version = new TextView(this);
        version.setText("V7.1");
        version.setTextSize(18);
        version.setGravity(Gravity.CENTER);

        EditText username = new EditText(this);
        username.setHint("اسم المستخدم");
        username.setSingleLine(true);

        EditText password = new EditText(this);
        password.setHint("كلمة المرور");
        password.setSingleLine(true);
        password.setInputType(
                InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        );

        Button loginButton = new Button(this);
        loginButton.setText("دخول");

        layout.addView(title);
        layout.addView(version);
        layout.addView(username);
        layout.addView(password);
        layout.addView(loginButton);

        setContentView(layout);

        loginButton.setOnClickListener(v -> {

            String user = username.getText().toString().trim();
            String pass = password.getText().toString();

            if (user.equals("admin") && pass.equals("Admin2026")) {
                showDashboard();
            } else {
                Toast.makeText(
                        MainActivity.this,
                        "اسم المستخدم أو كلمة المرور غير صحيحة",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void showDashboard() {

        setContentView(R.layout.activity_main);
    }
}