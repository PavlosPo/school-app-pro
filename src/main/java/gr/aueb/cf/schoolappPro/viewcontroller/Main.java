package gr.aueb.cf.schoolappPro.viewcontroller;

import java.awt.EventQueue;

public class Main {
	
	private static Login loginForm;
	private static AdminMenu adminMenu;
	private static TeachersMenu teachersMenu;
	private static StudentsMenu studentsMenu;
	private static UsersMenu usersMenu;
	private static SpecialitiesMenu specialitiesMenu;
	private static MeetingsMenu meetingsMenu;
	private static CitiesMenu citiesMenu;
	
	private static AdminInsertStudentsForm adminInsertStudentsForm;
	private static AdminInsertTeachersForm adminInsertTeachersForm;
	private static AdminInsertUsersForm adminInsertusersForm;
	private static AdminInsertSpecialitiesForm adminInsertSpecialitiesForm;
	private static AdminInsertMeetingsForm adminInsertMeetingsForm;
	private static AdminInsertCitiesForm adminInsertCitiesForm;
	
	private static AdminUpdateDeleteStudentsForm adminUpdateDeleteStudentsForm;
	private static AdminUpdateDeleteTeachersForm adminUpdateDeleteTeachersForm; 
	private static AdminUpdateDeleteUsersForm adminUpdateDeleteUsersForm;
	private static AdminUpdateDeleteSpecialitiesForm adminUpdateDeleteSpecialitiesForm;
	private static AdminUpdateDeleteMeetingsForm adminUpdateDeleteMeetingsForm;
	private static AdminUpdateDeleteCitiesForm adminUpdateDeleteCitiesForm; 
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginForm = new Login();
					loginForm.setVisible(true);
					
					adminMenu = new AdminMenu();
					adminMenu.setVisible(false);
					
					teachersMenu = new TeachersMenu();
					teachersMenu.setVisible(false);
					
					studentsMenu = new StudentsMenu();
					studentsMenu.setVisible(false);
					
					usersMenu = new UsersMenu();
					usersMenu.setVisible(false);
					
					specialitiesMenu = new SpecialitiesMenu();
					specialitiesMenu.setVisible(false);
					
					meetingsMenu = new MeetingsMenu();
					meetingsMenu.setVisible(false);
					
					citiesMenu = new CitiesMenu();
					citiesMenu.setVisible(false);
					
					adminInsertTeachersForm = new AdminInsertTeachersForm();
					adminInsertTeachersForm.setVisible(false);
					
					adminInsertStudentsForm = new AdminInsertStudentsForm();
					adminInsertStudentsForm.setVisible(false);

					adminInsertSpecialitiesForm = new AdminInsertSpecialitiesForm();
					adminInsertSpecialitiesForm.setVisible(false);

					adminInsertusersForm = new AdminInsertUsersForm();
					adminInsertusersForm.setVisible(false);

					adminInsertMeetingsForm = new AdminInsertMeetingsForm();
					adminInsertMeetingsForm.setVisible(false);

					adminInsertCitiesForm = new AdminInsertCitiesForm();
					adminInsertCitiesForm.setVisible(false);
					
					adminUpdateDeleteStudentsForm = new AdminUpdateDeleteStudentsForm();
					adminUpdateDeleteStudentsForm.setVisible(false);
					
					adminUpdateDeleteTeachersForm = new AdminUpdateDeleteTeachersForm();
					adminUpdateDeleteTeachersForm.setVisible(false);

					adminUpdateDeleteUsersForm = new AdminUpdateDeleteUsersForm();
					adminUpdateDeleteUsersForm.setVisible(false);

					adminUpdateDeleteSpecialitiesForm = new AdminUpdateDeleteSpecialitiesForm();
					adminUpdateDeleteSpecialitiesForm.setVisible(false);

					adminUpdateDeleteMeetingsForm = new AdminUpdateDeleteMeetingsForm();
					adminUpdateDeleteMeetingsForm.setVisible(false);

					adminUpdateDeleteCitiesForm = new AdminUpdateDeleteCitiesForm();
					adminUpdateDeleteCitiesForm.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static Login getLoginForm() {
		return loginForm;
	}

	public static AdminMenu getAdminMenu() {
		return adminMenu;
	}

	public static TeachersMenu getTeachersMenu() {
		return teachersMenu;
	}

	public static StudentsMenu getStudentsMenu() {
		return studentsMenu;
	}
	
	public static UsersMenu getUsersMenu() {
		return usersMenu;
	}

	public static SpecialitiesMenu getSpecialitiesMenu() {
		return specialitiesMenu;
	}

	public static MeetingsMenu getMeetingsMenu() {
		return meetingsMenu;
	}

	public static CitiesMenu getCitiesMenu() {
		return citiesMenu;
	}

	public static AdminInsertTeachersForm getAdminInsertTeacehrsForm() {
		return adminInsertTeachersForm;
	}
	
	public static AdminInsertStudentsForm getAdminInsertStudentsForm() {
		return adminInsertStudentsForm;
	}


	public static AdminUpdateDeleteStudentsForm getAdminUpdateDeleteStudentsForm() {
		return adminUpdateDeleteStudentsForm;
	}

	public static AdminUpdateDeleteTeachersForm getAdminUpdateDeleteTeachersForm() {
		return adminUpdateDeleteTeachersForm;
	}

	public static AdminInsertTeachersForm getAdminInsertTeachersForm() {
		return adminInsertTeachersForm;
	}

	public static AdminInsertUsersForm getAdminInsertusersForm() {
		return adminInsertusersForm;
	}

	public static AdminInsertSpecialitiesForm getAdminInsertSpecialitiesForm() {
		return adminInsertSpecialitiesForm;
	}

	public static AdminInsertMeetingsForm getAdminInsertMeetingsForm() {
		return adminInsertMeetingsForm;
	}

	public static AdminInsertCitiesForm getAdminInsertCitiesForm() {
		return adminInsertCitiesForm;
	}

	public static AdminUpdateDeleteUsersForm getAdminUpdateDeleteUsersForm() {
		return adminUpdateDeleteUsersForm;
	}

	public static AdminUpdateDeleteSpecialitiesForm getAdminUpdateDeleteSpecialitiesForm() {
		return adminUpdateDeleteSpecialitiesForm;
	}

	public static AdminUpdateDeleteMeetingsForm getAdminUpdateDeleteMeetingsForm() {
		return adminUpdateDeleteMeetingsForm;
	}

	public static AdminUpdateDeleteCitiesForm getAdminUpdateDeleteCitiesForm() {
		return adminUpdateDeleteCitiesForm;
	}

}