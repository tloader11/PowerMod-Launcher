package nl.powergamer.modpack.gui;

import java.awt.*;
import javax.swing.*;

public class MainGuiClass {

	public final static JFrame frame = new JFrame("PowerMod Launcher");
	public static void SetupInterface()
	{
		final JButton newAccountButton = new JButton("New Account...");
		
		JPanel contentPane = new JPanel();
	       contentPane.add(newAccountButton);

	       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	       frame.add(contentPane, BorderLayout.CENTER);
	       frame.setSize(300, 75);
	       frame.setLocationRelativeTo(null);
	       frame.setVisible(true);
	}
	
}
