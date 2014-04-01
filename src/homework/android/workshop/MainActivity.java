package homework.android.workshop;

import controllers.android.workshop.LoginController;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void login(View view) {
		LoginController uObj = new LoginController(
				(EditText) findViewById(R.id.edit_username),
				(EditText) findViewById(R.id.edit_password), this);

		uObj.login();
	}

	public void register(View view) {
		FragmentManager fm = getFragmentManager();
		RegisterLayout registerDialog = new RegisterLayout();
		registerDialog.setRetainInstance(true);
		registerDialog.show(fm, "fragment_name");
	}
}
