/*
 * Version1
 * Author: Ankush Kalash
 * Created Date: 16 Dec 2016
 * Details: This file contains various SQL Queries used in our scripts.
 * 			This returns the query to be passed in DBValidationQuery file.
 */

package pine.test.databasecon;

public class DBQueries {
	public static String getOTPByMobileNumber(String mobNo) throws Exception {
		Thread.sleep(3000);
		String query = "select otp from otpdetails where mobilenumber ='"+mobNo+"' order by id desc limit 1";
		
		return query;
	}
	
    public static String resetThrottlingAtPennyDrop(String pan) throws Exception{
        String query = "update leadwfsthrottling set count = 1 where lead_id in (select lead_id from leadprofile where pan = '"+pan+"') and workflowstate='BANK_DETAILS_VERIFIED';";
        return query;
 }
    		
 public static String leadInitiateOnboardingStatus(String pan) throws Exception{
        String query = "select status from leadtaskschedule where integrationstate = 'INITIATE_ONBOARDING'  and lead_id in (select lead_id from leadprofile where pan = '"+pan+"' and lead_id in (select id from lead where typeoflead='EPOS')) order by id desc limit 1";
        return query;
 }
 
 public static String updateStorePhotoMandatoryCheck(String mobNo) throws Exception{
     String query = "update leaddocument set ismandatory =0 where label = 'Store Photographs' and lead_id in (select id from lead where uniqueuserid = '"+mobNo+"');";
     System.out.println(query);
     return query;
}
 public static String usePlutusHUBDB () throws Exception{
	 String query = "use PLUTUS_HubDB; select getdate();";
	 return query;
 }
 public static String getHardwareIdfromClient(String clientId) throws Exception{
	 String query = "select HARDWARE_ID from PL_CLIENT_HARDWARE_DETAILS_TBL WHERE CLIENT_ID='"+clientId+"'";
	 System.out.println(query);
	 return query;
 }
 
 public static String getMerchantIdfromMerchant(String Merchantid) throws Exception{
	 String query = "select MERCHANT_ID from PLUI_MERCHANT_TBL WHERE MERCHANT_ID ='"+Merchantid+"'";
	 System.out.println(query);
	 return query;
 }
 

 
	public static String deleteLeadWithPAN(String mobNo) throws Exception{
		String query = "delete from leadstate where lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"');\r\n" + 
				"delete from leadkyc where entitytype_id =2 and entity_id in (select id from leadprofile where lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"'));\r\n" + 
				"delete from leadprofile where lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"');\r\n" + 
				"delete from leadcontact where entity_id in (select id from leadbusinessdetail where  lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"'));\r\n" + 
				"delete from leadaddress where entity_id in (select id from leadbusinessdetail where  lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"'));\r\n" + 
				"delete from leadkyc  where entity_id in (select id from leadbusinessdetail where  lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"'));\r\n" + 
				"delete from leaddocumentresource where leaddocument_id in (select id from leaddocument  where lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"'));\r\n" + 
				"delete from leaddocument where  lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"');\r\n" + 
				"delete from eposuserservice where leadid in (select id from lead where uniqueuserid ilike '"+mobNo+"');\r\n" + 
				"delete from leadstate where lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"');\r\n" + 
				"delete from leadbankaccount where lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"');\r\n" + 
				"delete from leadpackageinfo where lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"');\r\n" + 
				"delete from leadbusinessdetail where  lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"');\r\n" + 
				"delete from epos_order_item where order_id in (select id from epos_order where  lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"'));\r\n" + 
				"delete from payment_stg where order_id in (select id from epos_order where  lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"'));\r\n" + 
				"delete from leadmerchantagreement  where  lead_id  in (select id from lead where uniqueuserid ilike '"+mobNo+"');\r\n" + 
				"delete from leadwfsthrottling where (lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"' ) or uniqueuserid='"+mobNo+"');\r\n" + 
				"delete from leadmerchantresponse where lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"' );\r\n" + 
				"delete from epos_order where  lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"');\r\n" + 
				"delete from leadkyc  where entity_id in ( select id from leadindividualdetail where  lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"'));\r\n" + 
				"delete from leadcontact where entity_id in ( select id from leadindividualdetail where  lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"'));\r\n" + 
				"delete from leadaddress where entity_id in ( select id from leadindividualdetail where  lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"'));\r\n" + 
				"delete from leadindividualdetail where  lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"');\r\n" + 
				"delete from leadtaskschedule where lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"');\r\n" + 
				"delete from leadmerchantrisk where lead_id in (select id from lead where uniqueuserid ilike '"+mobNo+"');\r\n" + 
				"delete from lead where uniqueuserid ilike '"+mobNo+"';\r\n" + 
				"delete from eposuserpermission  where user_id =(select id from merchantonboardinguser where mobilenumber ilike '"+mobNo+"');\r\n" + 
				"delete from eposuserstoremapping where userid =(select id from merchantonboardinguser where mobilenumber ilike '"+mobNo+"');\r\n" + 
				"update digiterminal set status=1 where hardware_id in (select imei from merchantonboardinguser  where mobilenumber ilike '"+mobNo+"');\r\n" + 
				"delete from merchantonboardinguser where mobilenumber ilike '"+mobNo+"';";
		return query;
	}

	
}
