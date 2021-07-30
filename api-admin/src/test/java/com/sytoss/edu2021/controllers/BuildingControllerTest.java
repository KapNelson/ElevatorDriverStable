package com.sytoss.edu2021.controllers;


import com.sytoss.edu2021.ApiAdminApplication;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ApiAdminApplication.class)
class BuildingControllerTest {

    /*private String address = "test";
    private int floorsAmount = 9;
    private BuildingDTO buildingDTO;
    @Mock
    private BuildingRepository buildingRepository;

    @InjectMocks
    private BuildingController controller;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    @BeforeEach
    public void init()
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        buildingDTO = new BuildingDTO(address,floorsAmount);
    }

    @Test
    public void normalInsertResultTest() throws Exception {

        Gson gson = new Gson();
        String json = gson.toJson(buildingDTO);
        mockMvc.perform(post("/api/building").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(jsonPath("$.address")
                .value(address)).andExpect(jsonPath("$.floorsAmount")
                .value(floorsAmount)).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void normalSearchByAddressResultTest() throws Exception
    {
        mockMvc.perform(get("/api/building/find/address/"+address)).andDo(print())
                .andExpect(jsonPath("$.address")
                .value(address)).andExpect(jsonPath("$.floorsAmount")
                .value(floorsAmount)).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void zeroFloorsTest() throws Exception {
        String address = "Prospect Mira dom Kefira3";
        int floorsAmount = 0;
        buildingDTO = new BuildingDTO(address,floorsAmount);
        Gson gson = new Gson();
        String json = gson.toJson(buildingDTO);
        String response =  mockMvc.perform(post("/api/building").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
        Assert.assertEquals("Data is not valid",response);
    }

    @Test
    public void duplicateAddressesInsertTest() throws Exception {
        buildingDTO = new BuildingDTO(address,floorsAmount);
        Gson gson = new Gson();
        String json = gson.toJson(buildingDTO);
        String buildingBomString = mockMvc.perform(get("/api/building/find/address/"+address)).andReturn().getResponse().getContentAsString();
        BuildingBOM buildingBom = gson.fromJson(buildingBomString, BuildingBOM.class);
        String response =  mockMvc.perform(post("/api/building").contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
        Assert.assertEquals("Building with this address already registered. BuildingId=" + buildingBom.getId(),response);
    }*/
}








