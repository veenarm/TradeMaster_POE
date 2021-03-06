package listener.currencyPolling;

import gui.MainFrame;
import listener.currency.CurrencyBaseListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * What do I pay? - Currency Polling Tab Listener
 */
public class WhatDoIPayComboBoxListener extends CurrencyPollingBaseListener {

    private Logger LOG = LoggerFactory.getLogger(WhatDoIPayComboBoxListener.class);

    public WhatDoIPayComboBoxListener(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedPayItem = (String) ((JComboBox) e.getSource()).getSelectedItem();
        String selectedWantItem = (String) frame.getCurrencyPollerPanel().getCmbCurrencyTabWant().getSelectedItem();

        runCommonChecks(selectedPayItem, selectedWantItem);
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

}
