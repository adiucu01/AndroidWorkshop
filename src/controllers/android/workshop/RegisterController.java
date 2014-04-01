package controllers.android.workshop;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.EditText;
import homework.android.workshop.RegisterLayout;

public class RegisterController {

	public EditText _name;
	public EditText _email;
	public EditText _phone;
	public EditText _position;
	public EditText _password;
	public RegisterLayout _activity;

	public RegisterController(EditText edit_name, EditText edit_email,
			EditText edit_phone, EditText edit_position, EditText edit_password) {

		this._name = edit_name;
		this._email = edit_email;
		this._phone = edit_phone;
		this._position = edit_position;
		this._password = edit_password;
	}

	public void register() {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(
				"http://workshopz.apache.dev54.zitec.ro/users/");

		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("name", this._name
					.getText().toString()));
			nameValuePairs.add(new BasicNameValuePair("email", this._email
					.getText().toString()));
			nameValuePairs.add(new BasicNameValuePair("phone_number",
					this._phone.getText().toString()));
			nameValuePairs.add(new BasicNameValuePair("position",
					this._position.getText().toString()));
			nameValuePairs.add(new BasicNameValuePair("password", this
					.computeMD5Hash(this._password.getText().toString())));
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			// Execute HTTP Post Request
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			String content = EntityUtils.toString(entity);
			System.out.println(this.parseResponse(content));

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

	private String parseResponse(String response) {
		JSONObject mainObject = null;
		try {
			mainObject = new JSONObject(response);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String id = null;
		try {
			id = mainObject.getString("id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}

	private String computeMD5Hash(String password) {

		String response = null;
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest
					.getInstance("MD5");
			digest.update(password.getBytes());
			byte messageDigest[] = digest.digest();

			StringBuffer MD5Hash = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String h = Integer.toHexString(0xFF & messageDigest[i]);
				while (h.length() < 2)
					h = "0" + h;
				MD5Hash.append(h);
			}

			response = MD5Hash.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return response;

	}
}
