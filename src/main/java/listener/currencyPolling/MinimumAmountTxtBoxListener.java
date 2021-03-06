package listener.currencyPolling;

import gui.MainFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;

public class MinimumAmountTxtBoxListener extends CurrencyPollingDocumentBaseListener {
    private Logger LOG = LoggerFactory.getLogger(MinimumAmountTxtBoxListener.class);

    public MinimumAmountTxtBoxListener(MainFrame frame) {
        super(frame);
    }

    @Override
    public void eventChecks(DocumentEvent e) {
        frame.setValidAmountCurrencyInput(false);
        frame.getCurrencyPollerPanel().getUpdateButton().setEnabled(false);
        frame.getCurrencyPollerPanel().getCheckboxEnablePolling().setEnabled(false);

        try {
            Integer inputInt = Integer.valueOf(e.getDocument().getText(0, e.getDocument().getLength()));
            if (inputInt >= 1 && inputInt <= 10000) { // between 1 and 10000
                frame.setValidAmountCurrencyInput(true);
                if (frame.isValidMaxPayInput()) {
                    frame.getCurrencyPollerPanel().getUpdateButton().setEnabled(true);
                }
            }
        } catch (BadLocationException | NumberFormatException err) {
            LOG.error("MinimumAmountTxtBoxListener::eventChecks - Invalid number input, not re-updating : " + err.getMessage());
        }
    }

    @Override
    public Logger getLogger() {
        return LOG;
    }

}
