package my.com.tm.portal.leasing.constant;

import org.springframework.http.HttpStatus;

import my.com.tm.portal.leasing.model.ResponseStatus;

public final class Constants {

	private Constants() {}

	public static final String JSON_MOCK = "classpath:/mock/";
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final int DEFAULT_PAGE_NO = 1;
	public static final String HYPHEN = "-";
	public static final String UNDERSCORE = "_";
	public static final String HYPHEN_WITH_SPACES = " - ";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String SOA_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final String IMAGE_TYPE = "jpg";
	public static final String USER = "USER";
	public static final String ADMIN = "ADMIN";
	public static final String APP_PDF = "application/pdf";
	public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";
	public static final String SYSTEM = "SYSTEM";
	public static final String AUTHORITIES_KEY = "auth";
	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String ACCEPT = "Accept";
	public static final String MOCK_ON = "ON";
	public static final String MOCK_OFF = "OFF";
	public static final String REQUEST_ID = "RequestID";
	public static final String CORPORATE = "Corporate";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String COMPANY_CODE = "CompanyCode";
	public static final String CONTRACT_NO = "ContractNo";
	public static final String TERM_NO = "TermNo";
	public static final String START_DATE = "StartDate";
	public static final String END_DATE = "EndDate";
	public static final String SALES_TYPE = "SalesType";
	public static final String FREQUENCY_TYPE = "FrequencyType";
	public static final String ALL = "ALL";
	public static final String PARAMETERS = "parameters";
	public static final String SUCCESS = "SUCCESS";
	// CONTRACT
	public static final String CONTRACT_TYPE_CORPORATE = "Z004";
	public static final String TENANCY_AGREEMENT = "Z002";
	public static final String LICENCE_AGREEMENT = "Z003";
	public static final String TENANCY_AGREEMENT_STR = "TENANCY_AGREEMENT";
	public static final String LICENCE_AGREEMENT_STR = "LICENCE_AGREEMENT";
	public static final String FOOD_HALL_LICENCE_AGREEMENT = "Z009";
	public static final String GTO_CONTRACT_TYPES="Z002,Z003,Z009";
	// REQUEST and RESPONSE
	public static final String REQUEST_URL = "Request URL: ";
	public static final String RESPONSE_HEADER = "Response Header: ";
	public static final String RESPONSE_STATUS = "Response Status: ";
	public static final String REQUEST_PAYLOAD = "Request Payload: ";
	public static final String RESPONSE_PAYLOAD = "Response Payload: ";
	public static final String ENTRY_METHOD = "Entry Method: ";
	public static final String EXIT_METHOD = "Exit Method: ";
	// URL CONFIGURATION
	public static final String SECRET = "12345678abcdefghijklmn";
	public static final long EXPIRATION_TIME = 6000000; // 100 minutes
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String SWAGGER = "/swagger-ui.html#*";
	// PAYMENT MERCHANT
	public static final String IPAY_MERCHANT_CODE = "M05437";
	public static final String MYR = "MYR";
	// INVOICE LEVY
	public static final String POSTING_ACTIVITY_ZSHC40 = "ZSHC40";
	public static final String TAX_GROUP_S6 = "S6";

	public static final ResponseStatus SUCCESSFUL_RESPONSE = new ResponseStatus(HttpStatus.OK, SUCCESS);

	// FIT OUT DRAWING
	public static final String DOC_TYPE_FIT = "FIT";
	public static final String SERVICE_TYPE_LD = "LD";
	public static final String SHORT_DESC_ADMIN_LD = "admin LD";
	public static final String FILE_TYPE_PDF = ".pdf";
	public static final String SHORT_DESC_LD = "DMS LD";

}