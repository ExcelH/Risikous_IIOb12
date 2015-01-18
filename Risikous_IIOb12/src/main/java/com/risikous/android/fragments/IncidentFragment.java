package com.risikous.android.fragments;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.risikous.android.R;


import java.io.*;
import java.io.File;
import java.util.ArrayList;


public class IncidentFragment extends Fragment {


    private static final int REQUEST_CODE_PICK = 0;

    private ArrayAdapter<String> mfileAdapter = null;

    private ArrayList<String> mPathes = new ArrayList<String>();

    public IncidentFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_incident, container, false);

<<<<<<< HEAD
        final EditText incidentDescription_EditText = (EditText) v.findViewById(R.id.incidenDescription_EditText);
        final Spinner reportingOfArea_Spinner = (Spinner) v.findViewById(R.id.reportingOfArea_Spinner);
        final RadioGroup occurrenceRating_Group = (RadioGroup) v.findViewById(R.id.occurrenceRating_Group);
        final RadioGroup detectionRating_Group = (RadioGroup) v.findViewById(R.id.detectionRating_Group);
        final RadioGroup significance_Group = (RadioGroup) v.findViewById(R.id.significance_Group);
        final EditText date_EditText = (EditText) v.findViewById(R.id.date_EditText);
        date_EditText.setKeyListener(null);
        final EditText time_EditText = (EditText) v.findViewById(R.id.time_EditText);
        time_EditText.setKeyListener(null);
        final EditText location_EditText = (EditText) v.findViewById(R.id.location_EditText);
        final EditText immediateMeasure_EditText = (EditText) v.findViewById(R.id.immediateMeasure_EditText);
        final EditText consequences_EditText = (EditText) v.findViewById(R.id.consequences_EditText);
        final EditText personalFactors_EditText = (EditText) v.findViewById(R.id.personalFactors_EditText);
        final EditText organisationalFactors_EditText = (EditText) v.findViewById(R.id.organisationalFactors_EditText);
        final EditText additionalNotes_EditText = (EditText) v.findViewById(R.id.additionalNotes_EditText);
        final EditText contactInformation_EditText = (EditText) v.findViewById(R.id.contactInformation_EditText);


        mfileAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        Spinner lView = ((Spinner) v.findViewById(R.id.ListViewFiles));
        lView.setAdapter(mfileAdapter);
        mfileAdapter.add(getActivity().getString(R.string.incident_report_no_files));
        mfileAdapter.notifyDataSetChanged();

       lView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String selected = (String) parent.getItemAtPosition(position);
               if(selected.equals(getActivity().getString(R.string.incident_report_no_files))){
                   return;
               }
               if(!mPathes.isEmpty()){
                   if(mPathes.contains(selected)){
                       mPathes.remove(selected);
                   }
                   mfileAdapter.remove(selected);
                   mfileAdapter.notifyDataSetChanged();
               }
               if (mPathes.isEmpty() && !selected.equals(getActivity().getString(R.string.incident_report_no_files))) {
                   mfileAdapter.add(getActivity().getString(R.string.incident_report_no_files));
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }

       });

        //Date_Dialog
        v.findViewById(R.id.date_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mYear, mMonth, mDay;
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                StringBuilder sbDay = new StringBuilder();
                                StringBuilder sbMonth = new StringBuilder();
                                if (dayOfMonth < 10) sbDay.append("0").append(dayOfMonth);
                                else sbDay.append(dayOfMonth);
                                if (monthOfYear < 10) sbMonth.append("0").append(monthOfYear + 1);
                                else sbMonth.append(monthOfYear + 1);
                                date_EditText.setText(sbDay + "."
                                        + sbMonth + "." + year);
                            }
                        }, mYear, mMonth, mDay);
                dpd.show();
            }
        });

        //Time_Dialog
        v.findViewById(R.id.time_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mHour, mMinute;
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog tpd = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                StringBuilder sbMinute = new StringBuilder();
                                StringBuilder sbHour = new StringBuilder();
                                if (hourOfDay < 10) sbHour.append("0").append(hourOfDay);
                                else sbHour.append(hourOfDay);
                                if (minute < 10) sbMinute.append("0").append(minute + 1);
                                else sbMinute.append(minute);
                                time_EditText.setText(sbHour + ":" + sbMinute);
                            }
                        }, mHour, mMinute, true);
                tpd.show();
            }
        });

        //File_Dialog
=======
        mfileAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        ListView lView = ((ListView) v.findViewById(R.id.ListViewFiles));
        lView.setAdapter(mfileAdapter);
        lView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String[] string = mPathes.get(position).split("/");
                mfileAdapter.remove(string[string.length-1]);
                mfileAdapter.notifyDataSetChanged();
                mPathes.remove(position);
                if (mPathes.isEmpty()) {
                    mPathes.add(getActivity().getString(R.string.incident_report_no_files));
                }
                return true;
            }
        });
>>>>>>> parent of 7e79cec... Questionnaire Validation + PostRequest
        v.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                Intent i = Intent.createChooser(intent, "File");
                startActivityForResult(intent, REQUEST_CODE_PICK);
            }
        });

<<<<<<< HEAD
        //send_Post
        v.findViewById(R.id.publicationEnter_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Questionnaire questionnaire = new Questionnaire();
                ValidatorCollection vC = new ValidatorCollection();
                boolean validateStatus = true;

                questionnaire.setReportingArea(new ReportingArea(reportingOfArea_Spinner.getSelectedItem().toString()));
                if (!vC.validateRequired(questionnaire.getReportingArea().getName())) {
                    validateStatus = false;
                    Toast.makeText(getActivity(), "Bitte geben Sie einen Meldekreis an", Toast.LENGTH_SHORT).show();
                }

                questionnaire.setIncidentDescription(new IncidentDescription(incidentDescription_EditText.getText().toString()));
                if (!vC.validateRequired(questionnaire.getIncidentDescription().getName())) {
                    validateStatus = false;
                    Toast.makeText(getActivity(), "Bitte geben Sie eine Ereignisbeschreibung an.", Toast.LENGTH_SHORT).show();
                }
                if (!vC.validateCountChar(questionnaire.getIncidentDescription().getName(), 1000)) {
                    validateStatus = false;
                    Toast.makeText(getActivity(), "Das Feld 'Ereignisbeschreibung' darf höchstens 1000 Zeichen beinhalten.", Toast.LENGTH_SHORT).show();
                }

                final int occurrenceSelected = occurrenceRating_Group.getCheckedRadioButtonId();
                final RadioButton occurrenceRating_Button = (RadioButton) occurrenceRating_Group.findViewById(occurrenceSelected);
                if (occurrenceSelected != -1)
                    questionnaire.setOccurrenceRating(new OccurrenceRating(occurrenceRating_Button.getTag().toString()));
                else {
                    validateStatus = false;
                    Toast.makeText(getActivity(), "Bitte geben Sie die Eintrittswahrscheinlichkeit an.", Toast.LENGTH_SHORT).show();
                }

                final int detectionSelected = detectionRating_Group.getCheckedRadioButtonId();
                final RadioButton detectionRating_Button = (RadioButton) detectionRating_Group.findViewById(detectionSelected);
                if (detectionSelected != -1)
                    questionnaire.setDetectionRating(new DetectionRating(detectionRating_Button.getTag().toString()));
                else {
                    validateStatus = false;
                    Toast.makeText(getActivity(), "Bitte geben Sie die Entdeckungswahrscheinlichkeit an.", Toast.LENGTH_SHORT).show();
                }

                final int significanceSelected = significance_Group.getCheckedRadioButtonId();
                final RadioButton significance_Button = (RadioButton) significance_Group.findViewById(significanceSelected);
                if (significanceSelected != -1)
                    questionnaire.setSignificance(new Significance(significance_Button.getTag().toString()));
                else {
                    validateStatus = false;
                    Toast.makeText(getActivity(), "Bitte geben Sie die Priorität des Vorfalls an.", Toast.LENGTH_SHORT).show();
                }

                questionnaire.setDate(new Date(date_EditText.getText().toString()));
                questionnaire.setTime(new Time(time_EditText.getText().toString()));

                questionnaire.setLocation(new Location(location_EditText.getText().toString()));
                if (!questionnaire.getLocation().getName().equals("")) {
                    if (!vC.validateCountChar(questionnaire.getLocation().getName(), 5)) {
                        validateStatus = false;
                        Toast.makeText(getActivity(), "Das Feld 'Standort' darf höchstens 50 Zeichen beinhalten.", Toast.LENGTH_SHORT).show();
                    }
                }

                questionnaire.setImmediateMeasure(new ImmediateMeasure(immediateMeasure_EditText.getText().toString()));
                if (!questionnaire.getImmediateMeasure().getName().equals("")) {
                    if (!vC.validateCountChar(questionnaire.getImmediateMeasure().getName(), 1000)) {
                        validateStatus = false;
                        Toast.makeText(getActivity(), "Das Feld 'Sofortmaßnahme' darf höchstens 1000 Zeichen beinhalten.", Toast.LENGTH_SHORT).show();
                    }
                }

                questionnaire.setConsequences(new Consequences(consequences_EditText.getText().toString()));
                if (!questionnaire.getConsequences().getName().equals("")) {
                    if (!vC.validateCountChar(questionnaire.getConsequences().getName(), 1000)) {
                        validateStatus = false;
                        Toast.makeText(getActivity(), "Das Feld 'Folgen' darf höchstens 1000 Zeichen beinhalten.", Toast.LENGTH_SHORT).show();
                    }
                }

                questionnaire.setPersonalFactors(new PersonalFactors(personalFactors_EditText.getText().toString()));
                if (!questionnaire.getPersonalFactors().getName().equals("")) {
                    if (!vC.validateCountChar(questionnaire.getPersonalFactors().getName(), 1000)) {
                        validateStatus = false;
                        Toast.makeText(getActivity(), "Das Feld 'Personenbezogene Faktoren' darf höchstens 1000 Zeichen beinhalten.", Toast.LENGTH_SHORT).show();
                    }
                }

                questionnaire.setOrganisationalFactors(new OrganisationalFactors(organisationalFactors_EditText.getText().toString()));
                if (!questionnaire.getOrganisationalFactors().getName().equals("")) {
                    if (!vC.validateCountChar(questionnaire.getOrganisationalFactors().getName(), 1000)) {
                        validateStatus = false;
                        Toast.makeText(getActivity(), "Das Feld 'Organisatorische Faktoren' darf höchstens 1000 Zeichen beinhalten.", Toast.LENGTH_SHORT).show();
                    }
                }

                questionnaire.setAdditionalNotes(new AdditionalNotes(additionalNotes_EditText.getText().toString()));
                if (!questionnaire.getAdditionalNotes().getName().equals("")) {
                    if (!vC.validateCountChar(questionnaire.getAdditionalNotes().getName(), 1000)) {
                        validateStatus = false;
                        Toast.makeText(getActivity(), "Das Feld 'Weitere Anmerkungen' darf höchstens 1000 Zeichen beinhalten.", Toast.LENGTH_SHORT).show();
                    }
                }

                if(!mPathes.isEmpty()){
                    for(String currentPath : mPathes){
                        String[] path = currentPath.split(":");
                        java.io.File currentFile = new java.io.File(path[1]);
                        com.risikous.android.model.questionnaire.part.File file = new com.risikous.android.model.questionnaire.part.File();
                        file.setName(currentFile.getName());
                        Log.v("INFO TOM", currentFile.getAbsolutePath());
                        file.setFile(currentFile);
                        questionnaire.addAttachment(file);
                    }
                }


                questionnaire.setContactInformation(new ContactInformation(contactInformation_EditText.getText().toString()));
                if (!questionnaire.getContactInformation().getName().equals("")) {
                    if (!vC.validateCountChar(questionnaire.getContactInformation().getName(), 1000)) {
                        validateStatus = false;
                        Toast.makeText(getActivity(), "Das Feld 'Kontaktdaten' darf höchstens 1000 Zeichen beinhalten.", Toast.LENGTH_SHORT).show();
                    }
                }

                if (validateStatus) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    String xml = null;
                                    Toast.makeText(getActivity(), "Meldung verschickt.", Toast.LENGTH_SHORT).show();
                                    BuildPublication bP = new BuildPublication();
                                    xml = bP.buildQuestionnaire(questionnaire);
                                    System.out.println(xml);
                                    new POST(Constants.PUBLICATION_POST_URL, xml).execute();
                                    v.refreshDrawableState();
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    dialog.cancel();
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Wollen Sie diese Meldung veröffentlichen?").setPositiveButton("OK", dialogClickListener)
                            .setNegativeButton("Abbrechen", dialogClickListener).show();
                }

            }
        });


=======
>>>>>>> parent of 7e79cec... Questionnaire Validation + PostRequest
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_PICK) {
                String path = data.getData().toString();
                mPathes.add(path);
                Log.v("INFO TOM", path);
                String[] string = path.split("/");
                mfileAdapter.add(string[string.length - 1]);
                mfileAdapter.notifyDataSetChanged();
            }
        }

    }
}
