package com.github.handioq.diberapp.di.component;

import com.github.handioq.diberapp.di.module.PresenterModule;
import com.github.handioq.diberapp.di.scope.PresenterScope;
import com.github.handioq.diberapp.ui.addresses.AddressesFragment;
import com.github.handioq.diberapp.ui.auth.login.LoginFragment;
import com.github.handioq.diberapp.ui.auth.registration.SignupFragment;
import com.github.handioq.diberapp.ui.interaction.new_address.NewAddressFragment;
import com.github.handioq.diberapp.ui.interaction.new_order.NewOrderFragment;
import com.github.handioq.diberapp.ui.order.OrderFragment;
import com.github.handioq.diberapp.ui.orders.OrdersFragment;
import com.github.handioq.diberapp.ui.request.RequestFragment;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = PresenterModule.class)
public interface PresenterComponent {

    void inject(LoginFragment loginFragment);

    void inject(OrdersFragment ordersFragment);

    void inject(SignupFragment signupFragment);

    void inject(NewOrderFragment newOrderFragment);

    void inject(AddressesFragment addressesFragment);

    void inject(OrderFragment orderFragment);

    void inject(NewAddressFragment newAddressFragment);

    void inject(RequestFragment requestFragment);

}