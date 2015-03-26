package nl.powergamer.modpack.data;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFunction;
import com.teamdev.jxbrowser.chromium.JSValue;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicReference;

public class Account {
	public final String firstName;
	public final String lastName;
	public final String phone;
	public final String email;

	public Account(String firstName, String lastName, String phone, String email) {
	       this.firstName = firstName;
	       this.lastName = lastName;
	       this.phone = phone;
	       this.email = email;
	}
   @Override
   public String toString() 
	{
       return "Account{" +
       "firstName='" + firstName + '\'' +
       ", lastName='" + lastName + '\'' +
       ", phone='" + phone + '\'' +
       ", email='" + email + '\'' +
       '}';
   }
   public static Account createAccount(JFrame parent) {
       final AtomicReference<Account> result = new AtomicReference<Account>();
       final JDialog dialog = new JDialog(parent, "New Account", true);

       // Create Browser instance.
       final Browser browser = new Browser();
       // Register Java callback function that will be invoked from JavaScript
       // when user clicks New Account button.
       browser.registerFunction("onCreateAccount", new BrowserFunction() {
           @Override
           public JSValue invoke(JSValue... args) {
               // Read text field values received from JavaScript.
               String firstName = args[0].getString();
               String lastName = args[1].getString();
               String phone = args[2].getString();
               String email = args[3].getString();
               // Create a new Account instance.
               result.set(new Account(firstName, lastName, phone, email));
               // Close dialog.
               dialog.setVisible(false);
               // Return undefined (void) to JavaScript.
               return JSValue.createUndefined();
           }
       });
       // Load HTML with dialog's HTML+CSS+JavaScript UI.
       browser.loadHTML("<form class=\"form-horizontal\">\n<fieldset>\n\n<!-- Form Name -->\n<legend>Form Name</legend>\n\n<!-- Text input-->\n<div class=\"form-group\">\n  <label class=\"col-md-4 control-label\" for=\"username\">Username</label>  \n  <div class=\"col-md-4\">\n  <input id=\"username\" name=\"username\" type=\"text\" placeholder=\"type your username\" class=\"form-control input-md\" required=\"\">\n  <span class=\"help-block\">Your minecraft username</span>  \n  </div>\n</div>\n\n<!-- Password input-->\n<div class=\"form-group\">\n  <label class=\"col-md-4 control-label\" for=\"password\">Password</label>\n  <div class=\"col-md-4\">\n    <input id=\"password\" name=\"password\" type=\"password\" placeholder=\"super secret password\" class=\"form-control input-md\">\n    <span class=\"help-block\">Your minecraft password</span>\n  </div>\n</div>\n\n<!-- Multiple Checkboxes -->\n<div class=\"form-group\">\n  <label class=\"col-md-4 control-label\" for=\"remember\">Remember me</label>\n  <div class=\"col-md-4\">\n  <div class=\"checkbox\">\n    <label for=\"remember-0\">\n      <input type=\"checkbox\" name=\"remember\" id=\"remember-0\" value=\"true\">\n      Remember!\n    </label>\n\t</div>\n  </div>\n</div>\n\n<!-- Button -->\n<div class=\"form-group\">\n  <label class=\"col-md-4 control-label\" for=\"save\">Save account</label>\n  <div class=\"col-md-4\">\n    <button id=\"save\" name=\"save\" class=\"btn btn-primary\">Save</button>\n  </div>\n</div>\n\n</fieldset>\n</form>");

       dialog.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
               // Dispose Browser instance because we don't need it anymore.
               browser.dispose();
               // Close New Account dialog.
               dialog.setVisible(false);
               dialog.dispose();
           }
       });
       dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
       // Embed Browser Swing component into the dialog.
       dialog.add(new BrowserView(browser), BorderLayout.CENTER);
       dialog.setSize(400, 500);
       dialog.setResizable(false);
       dialog.setLocationRelativeTo(parent);
       dialog.setVisible(true);

       return result.get();
   }
}
