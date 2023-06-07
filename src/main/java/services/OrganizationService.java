package services;

import models.Organization;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface OrganizationService {

    @POST("/1/organizations")
    Call<Organization> createOrganization(@Query("displayName") String name, @Query("key") String apiKey, @Query("token") String apiToken);

}
