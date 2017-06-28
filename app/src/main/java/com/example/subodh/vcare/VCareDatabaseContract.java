package com.example.subodh.vcare;

import android.provider.BaseColumns;

/**
 * Created by udit jain on 6/17/2016.
 */
public class VCareDatabaseContract {
    public static final class MedicationListEntry implements BaseColumns{
        public static final String TABLE_NAME_MEDICATION="medication_list_table";
        public static final String ID_MEDICATION = "id";
        public static final String PATIENT_ID_MEDICATION = "patient_id";
        public static final String NAME_OF_MEDICATION = "medication_title";
    }
    public static final class DosListEntry implements BaseColumns{
        public static final String TABLE_NAME_DOS = "dos";
        public static final String ID_DOS = "id";
        public static final String PROBLEM_ID_DOS = "problem_id";
        public static final String NAME_OF_DOS = "dos";
    }
    public static final class DontsListEntry implements BaseColumns{
        public static final String TABLE_NAME_DONTS = "donts";
        public static final String ID_DONTS = "id";
        public static final String PROBLEM_ID_DONTS = "problem_id";
        public static final String NAME_OF_DONTS = "donts";
    }
    public static final class AllProblemsListEntry implements BaseColumns{
        public static final String TABLE_NAME_PROBLEM = "allproblems";
        public static final String ID_PROBLEM = "id";
        public static final String NAME_OF_PROBLEM = "problem";
    }
    public static final class PatientProblemsListEntry implements BaseColumns{
        public static final String TABLE_NAME_PATIENT_PROBLEM = "allpatientproblems";
        public static final String ID_PATIENT_PROBLEM = "id";
        public static final String PROBLEM_ID = "problem_id";
        public static final String PATIENT_ID = "patient_id";
        public static final String NAME_OF_PATIENT_PROBLEM = "problem";
    }
    public static final class PatientCheckupsListEntry implements BaseColumns{
        public static final String TABLE_NAME_PATIENT_CHECKUP = "patientcheckup";
        public static final String ID_PATIENT_CHECKUP = "id";
        public static final String PATIENT_ID = "patient_id";
        public static final String NAME_OF_CHECKUP = "checkup";
        public static final String FEES = "fees";
    }

}
