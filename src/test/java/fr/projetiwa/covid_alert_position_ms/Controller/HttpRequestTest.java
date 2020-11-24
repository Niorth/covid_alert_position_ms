package fr.projetiwa.covid_alert_position_ms.Controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
public class HttpRequestTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * to test if the route worked or not
     * @throws Exception
     */
    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        header.add(HttpHeaders.AUTHORIZATION, "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI1VmxhaXlBMGVaZW9rVnl4STFMT25tc1FEM3pjME1wajVPYUdGbnZHYmZFIn0.eyJleHAiOjE2MDU4OTgxNDQsImlhdCI6MTYwNTg5Nzg0NCwiYXV0aF90aW1lIjoxNjA1ODk3ODQzLCJqdGkiOiIwMzA5MTBmYy03YTE4LTQ3ZmQtOTc0Yi0zY2FhMmViZTA0MTUiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvY292aWQtYWxlcnQiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiNTAzMGE4YTMtYmI4Yy00MmQwLTg3ZTItMDMwZDcxODFhMGNkIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidXNlci1hcHAiLCJub25jZSI6IjliYTU0ZmFjLTE4OGQtNDA3NC04MzI2LTIwMTk0ODY4MjZhNyIsInNlc3Npb25fc3RhdGUiOiJkZDEwNzFmZS1hZDdlLTQ3MzktODQ0Ni05MWQxNjNkZjRjZWIiLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJ1c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiZm9vIn0.bajbyUvmnAp9fLVMAmQrs_EIdoSv4rWtNKATuVHJmpC4TZgvUsSSGs7fhJQHjE6Dp5FaCwbvFaKDw2GlPjVwxv4VgvaBQ2G4cBLy4-qj1kJQK5sV541mDu9B4pbYa68J2kl2cZHNobcVKCmMV96Av6YXLH5CLsYB0YHe7Q5fpzLVrbooSHXYiZKpso3LfRBYh1G2VFLMTCSp1b74AjCTZ_3tOweBov1YwVRuByDwlIZxnShdAmihUJinu_zYL06MaI24vjUjlCla7xvdeV5JpYsCrUd70Ah4eit1Bic-O_jLQbhWOLbj5EJMHV2yQHHTs_m1swtgpyrR5YsoEmO_mQ");

        this.mockMvc.perform(get("/positions").headers(header)).andDo(print())
                .andExpect(status().isOk());

    }

    /**
     * to test if the route worked or not
     * @throws Exception
     */
    @Test
    public void setPostionToSuspicousHttpTest() throws Exception{
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        header.add(HttpHeaders.AUTHORIZATION, "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI1VmxhaXlBMGVaZW9rVnl4STFMT25tc1FEM3pjME1wajVPYUdGbnZHYmZFIn0.eyJleHAiOjE2MDU4OTgxNDQsImlhdCI6MTYwNTg5Nzg0NCwiYXV0aF90aW1lIjoxNjA1ODk3ODQzLCJqdGkiOiIwMzA5MTBmYy03YTE4LTQ3ZmQtOTc0Yi0zY2FhMmViZTA0MTUiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvY292aWQtYWxlcnQiLCJhdWQiOiJhY2NvdW50Iiwic3ViIjoiNTAzMGE4YTMtYmI4Yy00MmQwLTg3ZTItMDMwZDcxODFhMGNkIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoidXNlci1hcHAiLCJub25jZSI6IjliYTU0ZmFjLTE4OGQtNDA3NC04MzI2LTIwMTk0ODY4MjZhNyIsInNlc3Npb25fc3RhdGUiOiJkZDEwNzFmZS1hZDdlLTQ3MzktODQ0Ni05MWQxNjNkZjRjZWIiLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbIioiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJ1c2VyIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwicHJlZmVycmVkX3VzZXJuYW1lIjoiZm9vIn0.bajbyUvmnAp9fLVMAmQrs_EIdoSv4rWtNKATuVHJmpC4TZgvUsSSGs7fhJQHjE6Dp5FaCwbvFaKDw2GlPjVwxv4VgvaBQ2G4cBLy4-qj1kJQK5sV541mDu9B4pbYa68J2kl2cZHNobcVKCmMV96Av6YXLH5CLsYB0YHe7Q5fpzLVrbooSHXYiZKpso3LfRBYh1G2VFLMTCSp1b74AjCTZ_3tOweBov1YwVRuByDwlIZxnShdAmihUJinu_zYL06MaI24vjUjlCla7xvdeV5JpYsCrUd70Ah4eit1Bic-O_jLQbhWOLbj5EJMHV2yQHHTs_m1swtgpyrR5YsoEmO_mQ");
        this.mockMvc.perform(get("/positions/setSuspicious").headers(header)).andDo(print());

    }
}