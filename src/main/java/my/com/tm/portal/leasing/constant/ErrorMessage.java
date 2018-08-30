package my.com.tm.portal.leasing.constant;

public final class ErrorMessage {

	private ErrorMessage() {}

	public static final String NO_RECORDS_FOUND = "No Records Found";
	public static final String ALREADY_EXISTS = "Already exist";
	public static final String JSON_FILE_IS_EMPTY = "JSON file is empty";
	public static final String EMPTY_UTILITY_TREND_DURATION = "Utility Trend Duration cannot be empty";
	public static final String WSO2_COMPLETE_ACTION_FAILED = "Task Status update failed.";
	public static final String WSO2_PROCESS_INSTANCE_ID_MISSING = "Process Instance ID is missing.";
	public static final String PROCUREMENT_SUBMIT_MESSAGE = "Your procurement status must be in Draft mode before submitting.";
	public static final String PROCUREMENT_ERROR_SAVE_MESSAGE = "Your procurement status must be in Draft / Tenant Rejected mode before saving.";
	public static final String PROCUREMENT_DRAFT_ONLY = "Your procurement status must be in Draft mode.";
	public static final String CATEGORY_FIELD_REQUIRED = "Category field is required";
	public static final String PURCHASING_GROUP_FIELD_REQUIRED = "Purchasing Group field is required";
	public static final String GENERIC_ERROR = "An Error has occured. Please contact system administrator";

	public static final String MISSING_REQUIRED_PARAMETER = "Missing required parameter.";
}
