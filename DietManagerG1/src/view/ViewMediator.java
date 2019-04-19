package view;

public class ViewMediator {

    private GUIComponent mainGUI;

    public void setPanel(GUIComponent mainGUI) {
        this.mainGUI = mainGUI;
    }

    public void updateDailyValues() {
    	mainGUI.updateValues();
    }

    public void updatePanel() {
    	mainGUI.updatePanel();
    }
}
