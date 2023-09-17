## API Implement

1. Request 的API使用輸入參數傳入三個必要參數，並執行 URL 就可以返回匯入轉換的請求
* [http://localhost:8080/exchangerate/calculate?source=USD&target=JPY&amount=$1,525](http://localhost:8080/exchangerate/calculate?source=USD&target=JPY&amount=$1,525 )
2. 單元測試如下，根據檢視返回的 status code 和 測試資料的返回 json 是否正確
```
@RunWith(JUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AsiayoApiTestApplicationTests {
    @Autowired private MockMvc mvc;

    @Test
    void contextLoads() throws Exception {
        System.out.println("contextLoads");
    }


    @Test
    void testExchangeRateApi() throws Exception {
        String uri = "/exchangerate/calculate?source=USD&target=JPY&amount=$1,525";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();

        int status = result.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        String message = result.getResponse().getContentAsString();
        Assertions.assertEquals("{\"msg\":\"success\",\"amount\":\"$170496.53\"}", message);
        System.out.println(message);
    }
}
```
