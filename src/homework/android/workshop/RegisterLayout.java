package homework.android.workshop;

import controllers.android.workshop.RegisterController;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class RegisterLayout extends DialogFragment {

	public RegisterLayout() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.register, container);
		getDialog().setTitle("User registration");
		Button btn_register = (Button) view.findViewById(R.id.btn_register);

		btn_register.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				RegisterController rObj = new RegisterController(
						(EditText) view.findViewById(R.id.edit_name),
						(EditText) view.findViewById(R.id.edit_email),
						(EditText) view.findViewById(R.id.edit_phone),
						(EditText) view.findViewById(R.id.edit_position),
						(EditText) view.findViewById(R.id.edit_password));

				rObj.register();
				getDialog().dismiss();
			}
		});

		return view;
	}
}
