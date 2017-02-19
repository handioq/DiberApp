package com.github.handioq.diberapp.ui.interaction.new_order;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.github.handioq.diberapp.R;
import com.github.handioq.diberapp.application.DiberApp;
import com.github.handioq.diberapp.base.BaseFragment;
import com.github.handioq.diberapp.model.dto.ShopDto;
import com.github.handioq.diberapp.model.dvo.AddressDvo;
import com.github.handioq.diberapp.model.dvo.OrderDvo;
import com.github.handioq.diberapp.model.dvo.ShopDvo;
import com.github.handioq.diberapp.ui.addresses.AddressesMvp;
import com.github.handioq.diberapp.ui.dialog.NewShopDialog;
import com.github.handioq.diberapp.ui.shops.ShopsMvp;
import com.github.handioq.diberapp.util.AuthPreferences;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class NewOrderFragment extends BaseFragment implements NewOrderMvp.View, NewShopDialog.DialogListener,
    ShopsMvp.View, AddressesMvp.View {

    private static final String ADD_SHOP_DIALOG = "NewShopDialog";
    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.spinner_addresses)
    Spinner addrSpinnerView;

    @BindView(R.id.address_from_edittext)
    AutoCompleteTextView addressFromEditView;

    @Inject
    ShopsMvp.Presenter shopsPresenter;

    @Inject
    AddressesMvp.Presenter addressesPresenter;

    @Inject
    AuthPreferences authPreferences;

    public static NewOrderFragment newInstance() {
        NewOrderFragment fragment = new NewOrderFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_order, container, false);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((DiberApp) getContext().getApplicationContext()).getPresenterComponent().inject(this);

        shopsPresenter.setView(this);
        shopsPresenter.getUserShops(authPreferences.getUserId());

        addressesPresenter.setView(this);
        addressesPresenter.getUserAddresses(authPreferences.getUserId());
    }

    private void initAddressesSpinner(List<AddressDvo> addresses) {
        ArrayList<String> strAddresses = new ArrayList<>();

        for (AddressDvo addressDvo : addresses) {
            strAddresses.add(addressDvo.getName());
        }

        ArrayAdapter<String> countryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, strAddresses);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        addrSpinnerView.setAdapter(countryAdapter);
        //addrSpinnerView.setSelection(countryAdapter.getPosition();
    }

    private void initShopsSpinner(List<ShopDvo> shops) {

        ArrayList<String> strShops = new ArrayList<>();
        for (ShopDvo shopDvo : shops) {
            strShops.add(shopDvo.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.select_dialog_singlechoice, strShops);
        addressFromEditView.setThreshold(2);
        addressFromEditView.setAdapter(adapter);

        addressFromEditView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // todo send query to server or smth
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    // New order methods:

    @Override
    public void showAddOrderProgress() {

    }

    @Override
    public void hideAddOrderProgress() {

    }

    @Override
    public void onOrderAdded(OrderDvo orderDvo) {

    }

    @Override
    public void onAddOrderError(Throwable error) {

    }

    @OnClick(R.id.addShopButton)
    public void addShopClick() {
        NewShopDialog dialog = new NewShopDialog();
        dialog.attachListener(this);
        dialog.show(getFragmentManager(), ADD_SHOP_DIALOG);
    }

    // New shop dialog:

    @Override
    public void onNewShopDialogAddClick(DialogFragment dialog, ShopDto shopDto) {

    }

    @Override
    public void onNewShopDialogCancelClick(DialogFragment dialog) {

    }

    // Shops methods:

    @Override
    public void showLoadShopsProgress() {

    }

    @Override
    public void hideLoadShopsProgress() {

    }

    @Override
    public void setShops(List<ShopDvo> shops) {
        initShopsSpinner(shops);
    }

    @Override
    public void showLoadShopsError(Throwable error) {
        Log.e(TAG, error.toString());
    }

    // Addresses methods:

    @Override
    public void showLoadAddressesProgress() {

    }

    @Override
    public void hideLoadAddressesProgress() {

    }

    @Override
    public void setAddresses(List<AddressDvo> addresses) {
        initAddressesSpinner(addresses);
    }

    @Override
    public void showLoadAddressesError(Throwable error) {

    }

}
