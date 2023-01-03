package com.gorest.testbase;

import com.gorest.constants.Path;
import com.gorest.utils.PropertyReader;
import com.gorest.utils.PropertyReader;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TerstBase extends TestUtils {
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init() {
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
      //  RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
         RestAssured.basePath = Path.PUBLIC;
    }
}
