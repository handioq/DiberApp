package com.github.handioq.diberapp.network;

import com.github.handioq.diberapp.model.dto.AddressDto;
import com.github.handioq.diberapp.model.dto.AuthResponseDto;
import com.github.handioq.diberapp.model.dto.NewOrderDto;
import com.github.handioq.diberapp.model.dto.OrderDto;
import com.github.handioq.diberapp.model.dto.PageableAddressListDto;
import com.github.handioq.diberapp.model.dto.PageableOrderListDto;
import com.github.handioq.diberapp.model.dto.RegisterDto;
import com.github.handioq.diberapp.model.dto.RequestDto;
import com.github.handioq.diberapp.model.dto.RequestStatusDto;
import com.github.handioq.diberapp.model.dto.ResponseDto;
import com.github.handioq.diberapp.model.dto.ReviewDto;
import com.github.handioq.diberapp.model.dto.ShopDto;
import com.github.handioq.diberapp.model.dto.UserDto;
import com.github.handioq.diberapp.model.dto.UserInfoDto;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    String API_BASE_URL = "/api/v1";

    String LOGIN_URL = "/oauth/token";
    String SIGNUP_URL = API_BASE_URL + "/auth/register";

    String USER_GET_INFO = API_BASE_URL + "/users/info";
    String USER_ORDERS = API_BASE_URL + "/users/{user_id}/orders";
    String USER_SHOPS = API_BASE_URL + "/users/{user_id}/shops";
    String USER_SHOPS_ID = USER_SHOPS + "/{shop_id}";
    String USER_ADDRESSES = API_BASE_URL + "/users/{user_id}/addresses";
    String USER_ADDRESSES_ID = USER_ADDRESSES + "/{address_id}";
    String ORDER_INFO = API_BASE_URL + "/orders/{order_id}";
    String ORDER_ID = API_BASE_URL + "/orders/{order_id}";
    String ORDER_REQUESTS = API_BASE_URL + "/orders/{order_id}/requests";
    String REQUEST_INFO = API_BASE_URL + "/requests/{request_id}";
    String USER_REVIEWS = API_BASE_URL + "/users/{user_id}/reviews";
    String REQUEST_STATUS = API_BASE_URL + "/requests/{request_id}/status";

    @POST(LOGIN_URL)
    Observable<AuthResponseDto> login(@Query("username") String login,
                                      @Query("password") String password,
                                      @Query(NetworkConstants.GRANT_TYPE) String grantType,
                                      @Query(NetworkConstants.CLIENT_ID) String clientId);

    @POST(SIGNUP_URL)
    Observable<UserDto> signup(@Body RegisterDto registerDto);

    @GET(USER_GET_INFO)
    Observable<UserInfoDto> getUserInfo();

    @GET(USER_ORDERS)
    Observable<PageableOrderListDto> getUserOrders(@Path("user_id") long userId);

    @GET(USER_REVIEWS)
    Observable<List<ReviewDto>> getUserReviews(@Path("user_id") long userId);

    @POST(USER_ORDERS)
    Observable<OrderDto> addOrder(@Path("user_id") long userId,
                                  @Body NewOrderDto orderDto);

    @POST(USER_SHOPS)
    Observable<ShopDto> addShop(@Path("user_id") long userId,
                                @Body ShopDto shopDto);

    @POST(USER_ADDRESSES)
    Observable<AddressDto> addAddress(@Path("user_id") long userId,
                                      @Body AddressDto addressDto);

    @GET(USER_SHOPS)
    Observable<List<ShopDto>> getUserShops(@Path("user_id") long userId);

    @GET(USER_ADDRESSES)
    Observable<PageableAddressListDto> getUserAddresses(@Path("user_id") long userId);

    @GET(ORDER_REQUESTS)
    Observable<List<RequestDto>> getOrderRequests(@Path("order_id") long orderId);

    @GET(ORDER_INFO)
    Observable<OrderDto> getOrder(@Path("order_id") long orderId);

    @GET(REQUEST_INFO)
    Observable<RequestDto> getRequestDetails(@Path("request_id") long requestId);

    @DELETE(USER_ADDRESSES_ID)
    Observable<ResponseDto> removeAddress(@Path("user_id") long userId,
                                          @Path("address_id") int addressId);

    @DELETE(USER_SHOPS_ID)
    Observable<ResponseDto> removeShop(@Path("user_id") long userId,
                                       @Path("shop_id") int shopId);

    @DELETE(ORDER_ID)
    Observable<ResponseDto> deleteOrder(@Path("order_id") long orderId);

    @PUT(REQUEST_STATUS)
    Observable<RequestDto> acceptRequest(@Path("request_id") long requestId, @Body RequestStatusDto requestStatusDto);

    @PUT(REQUEST_STATUS)
    Observable<RequestDto> declineRequest(@Path("request_id") long requestId, @Body RequestStatusDto requestStatusDto);

}