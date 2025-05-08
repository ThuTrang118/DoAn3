package dataproviders;

import org.testng.annotations.DataProvider;
import utils.ExcelUtils;
import java.io.IOException;

public class LoginDataProvider {
	@DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        String filePath = "src/test/resources/LoginData.xlsx";
        return ExcelUtils.readExcelData(filePath, "Sheet1");
    }
}