package nl.powergamer.modpack;

import nl.powergamer.modpack.data.Account;
import nl.powergamer.modpack.gui.MainGuiClass;

public class MainClass 
{
	public static void main(String[] args) 
	{
		MainGuiClass.SetupInterface();
		Account.createAccount(MainGuiClass.frame);
	}
}
