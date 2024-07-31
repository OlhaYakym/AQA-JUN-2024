package org.prog.testng;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.prog.dto.PersonDto;
import org.prog.dto.ResponseDto;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestTests {

    @Test
    public void getRandomUser() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.queryParam("inc", "gender,name,nat,location");
        requestSpecification.queryParam("noinfo");
        requestSpecification.queryParam("results", 10);
        requestSpecification.baseUri("https://randomuser.me/");
        requestSpecification.basePath("/api");

        Response response = requestSpecification.get();
        response.prettyPrint();

        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);

        ResponseDto dto = response.as(ResponseDto.class);
        Assert.assertEquals(dto.getResults().size(), 10,
                "Requested 10 results but got " + dto.getResults().size());

        for (PersonDto person : dto.getResults()) {
            Assert.assertNotNull(person.getLocation(),
                    "Location should not be null for person with gender " + person.getGender());
            Assert.assertNotNull(person.getLocation().getCity(),
                    "City should not be null for person with gender " + person.getGender());
            Assert.assertNotNull(person.getLocation().getCoordinates(),
                    "Coordinates should not be null for person with gender " + person.getGender());
            Assert.assertNotNull(person.getLocation().getTimezone(),
                    "Timezone should not be null for person with gender " + person.getGender());
        }
    }
}
