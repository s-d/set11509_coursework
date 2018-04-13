package trade;

import global.GlobalControlCodes;
import global.controller.Controller;
import trade.model.TradeModel;
import trade.view.TradeViewForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Observable;

public class TradeController extends Observable
        implements Controller, ActionListener, Serializable {
    private TradeModel tradeModel;
    private TradeViewForm tradeView;

    public void initialiseController() {
        tradeModel = new TradeModel();
        tradeView = new TradeViewForm();

        linkMVC();
        addActionListeners();
    }

    public void initialiseTable(String fromDate, String tillDate,
                                String companyCode, String sellerBuyer,
                                String broker) {
        tradeModel.searchTrades(fromDate, tillDate, companyCode,
                sellerBuyer, broker);

    }

    private void linkMVC() {
        tradeModel.addObserver(tradeView);
    }

    private void addActionListeners() {
        tradeView.setActionListeners(this);
    }

    public void showView() {
        tradeView.showView();
    }

    public void closeView() {
        tradeView.closeView();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(GlobalControlCodes.TRADE_SEARCH.name())) {
            tradeModel.searchTrades(tradeView.getFromValue(),
                    tradeView.getTillValue(), tradeView.getCompanyValue(),
                    tradeView.getSellerBuyerValue(), tradeView.getBrokerValue());
        }
        if (e.getActionCommand().equals(GlobalControlCodes.TRADE_CLOSE.name())) {
            setChanged();
            notifyObservers(GlobalControlCodes.TRADE_CLOSE);
        }
    }
}
