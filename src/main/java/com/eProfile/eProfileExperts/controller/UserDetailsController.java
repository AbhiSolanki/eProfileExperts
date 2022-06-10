package com.eProfile.eProfileExperts.controller;

import com.eProfile.eProfileExperts.dto.UserDetailsDto;
import com.eProfile.eProfileExperts.utils.FileDateFormatter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class UserDetailsController {

    @GetMapping(value = "/getDetails")
    public String getDetails(){
        return "Welcome";
    }

    @PostMapping(value = "/addUserDetails")
    public ResponseEntity<String> addUsers(@RequestBody UserDetailsDto userDetailsDto){

        String fileName = "eProfileExpertData"+ FileDateFormatter.createFileName()+".xls";
        Path file = Paths.get(fileName);
        if(Files.exists(file)){
            try {
                FileInputStream myxls = new FileInputStream(fileName);
                HSSFWorkbook studentsSheet = new HSSFWorkbook(myxls);
                HSSFSheet worksheet = studentsSheet.getSheetAt(0);
                int lastRow=worksheet.getLastRowNum();
                System.out.println(lastRow);
                Row row = worksheet.createRow(++lastRow);
               String date =  new SimpleDateFormat("MM/dd/yyyy").format(userDetailsDto.getServicedate());
                row.createCell(0).setCellValue(userDetailsDto.getService());
                row.createCell(1).setCellValue(date);
                row.createCell(2).setCellValue(userDetailsDto.getName());
                row.createCell(3).setCellValue(userDetailsDto.getPhone());
                row.createCell(4).setCellValue(userDetailsDto.getEmail());
                row.createCell(5).setCellValue(userDetailsDto.getAddress());
                row.createCell(6).setCellValue(userDetailsDto.getBriefServiceDes());

                myxls.close();
                FileOutputStream output_file =new FileOutputStream(new File(fileName));
                        //write changes
                        studentsSheet.write(output_file);
                output_file.close();
                System.out.println(" is successfully written");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            createXlsFile(userDetailsDto,fileName);
        }

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    private static void createXlsFile(UserDetailsDto dto,String fileName){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Eprofile sheet");
        Map< String, Object[]> data = new HashMap<String, Object[]>();
        data.put("1", new Object[] {
                "Service",
                "Service Date",
                "Name",
                "Phone",
                "Email",
                "Address",
                "Brief Service Description"
        });

        data.put("2",new Object[] {
                dto.getService(),
                dto.getServicedate(),
                dto.getName(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getBriefServiceDes()
        });
        String date =  new SimpleDateFormat("MM/dd/yyyy").format(dto.getServicedate());

        Set< String > keyset = data.keySet();
        int rownum = 0;
        for (String key: keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj: objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof Date)
                    cell.setCellValue(new SimpleDateFormat("MM/dd/yyyy").format( obj));
                else if (obj instanceof Boolean)
                    cell.setCellValue((Boolean) obj);
                else if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Double)
                    cell.setCellValue((Double) obj);
            }
        }

        try {
            FileOutputStream out =
                    new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
