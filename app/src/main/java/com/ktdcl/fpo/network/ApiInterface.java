package com.ktdcl.fpo.network;
import com.ktdcl.fpo.model.BankNameModel;
import com.ktdcl.fpo.model.DistrictModel;
import com.ktdcl.fpo.model.FPOAppModel;
import com.ktdcl.fpo.model.LetterModel;
import com.ktdcl.fpo.model.ResponseModel;
import com.ktdcl.fpo.model.RevenueData;
import com.ktdcl.fpo.model.StaffModel;
import com.ktdcl.fpo.model.TalukModel;
import com.ktdcl.fpo.model.VillageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
     */

   /* //mine AIzaSyD_Zbbwx7aYQaAWnl5O2Dv4-6r2G3dhEUI
    //ind AIzaSyDexSpfSK4WI1XnxQCuusnateV57knMJww
    @GET("api/place/nearbysearch/json?sensor=true&rankby=distance&key=AIzaSyDexSpfSK4WI1XnxQCuusnateV57knMJww")
    Call<Place> getNearbyPlaces(@Query("types") String type, @Query("location") String location);
    //Call<Place> getNearbyPlaces(@Query("location") String location);

    @GET("api/place/nearbysearch/json?sensor=true&rankby=distance&key=AIzaSyDexSpfSK4WI1XnxQCuusnateV57knMJww")
    Call<Place> getNearbyPlacesWithToken(@Query("location") String location, @Query("pagetoken") String token);*/

    // with type
    //Call<Place> getNearbyPlaces(@Query("types") String type, @Query("location") String location);

    // Call<Place> getNearbyPlaces(@Query("types") String type, @Query("location") String location, @Query("radius") int radius);

    // @GET("beneficiary/getByMobile.php")
    //  Call<BeneficiaryModel> getByMobile(@Query("mobile") String mobile);

    /*    @GET("getTaskByExeId.php")
        Call<List<AssignedTasksModel>> getTaskAssignedToExe(@Query("id") String id);

        @GET("VerifyUser.php")
        Call<ExecVerifyModel> verifyUser(@Query("uname") String uname, @Query("pwd") String pwd);

        @GET("getAllProducts.php")
        Call<List<ProductsOnlyModel>> getAllProducts(@Query("name") String name);

        @GET("getProductDetails.php")
        Call<List<ProductDetails>> getProductDetails(@Query("pid") String name);
     */
    @GET("getAllDistricts.php")
    Call<List<DistrictModel>> getAllDistricts();

    @GET("getAllTaluks.php")
    Call<List<TalukModel>> getTaluks(@Query("dist_id") String dist_id);

    @GET("getAllTandasByTLK.php")
    Call<List<VillageModel>> getTandasByTaluk(@Query("tlk_id") String tlk_id);

    @GET("BankList.php")
    Call<List<BankNameModel>> getBanks();

    @POST("InsertStaffDetails.php")
    Call<ResponseModel> insertStaff(@Body StaffModel staff);

    @POST("InsertLetterDetails.php")
    Call<ResponseModel> insertLetter(@Body LetterModel letterModel);

    @POST("addRevenueData.php")
    Call<ResponseModel> insertRevenue(@Body RevenueData letterModel);

    @POST("InsertFPODetails.php")
    Call<ResponseModel> insertFPO(@Body FPOAppModel fpoAppModel);

}
