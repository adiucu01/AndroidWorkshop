package controllers.android.workshop;

import homework.android.workshop.MainActivity;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class LoginController {

	public EditText _username;
	public EditText _password;
	public MainActivity _activity;

	public LoginController(EditText edit_username, EditText edit_password,
			MainActivity activity) {
		this._username = edit_username;
		this._password = edit_password;
		this._activity = activity;
	}

	public void login() {
		if (this._username.getText().toString().equals("admin")
				&& this._password.getText().toString().equals("admin")) {

			Toast.makeText(this._activity,
					"Welcome " + this._username.getText().toString(), Toast.LENGTH_SHORT)
					.show();
		}
	}
}
