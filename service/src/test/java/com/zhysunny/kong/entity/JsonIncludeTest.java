package com.zhysunny.kong.entity;

import static org.junit.Assert.*;
import com.zhysunny.common.json.JsonUtils;
import org.junit.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * JsonIncludeTest Test.
 * @author zhysunny
 * @date 2020/8/26 23:26
 */
public class JsonIncludeTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.out.println("Test JsonIncludeTest Class Start...");
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {
        System.out.println("Test JsonIncludeTest Class End...");
    }

    @Test
    public void test() throws Exception {
        Service service = Service.DEFAULT;
        String json = JsonUtils.toJson(service);
        System.out.println(json);
    }
}