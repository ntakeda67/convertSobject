package org.nt67.convSobject

import com.github.mygreen.supercsv.annotation.CsvBean
import com.github.mygreen.supercsv.annotation.CsvColumn

@CsvBean(header = true, validateHeader = true)
data class SFExportCSV(
        // ParseProcessorがgetterを要求するのでvar
        @CsvColumn(label="ID")        var id:String = "",
        @CsvColumn(label="LASTNAME")  var lastName: String = "",
        @CsvColumn(label="FIRSTNAME") var firstName: String = "",
        @CsvColumn(label="EMAIL")     var email:String = "",
        @CsvColumn(label="TITLE")     var title:String = "",
        @CsvColumn(label="LEVEL__C")  var level__c: String = ""
)

@CsvBean(header = true, validateHeader = true)
data class OtherSystemCSV(
        @CsvColumn(number=1, label="名前")  var lastName: String = "",
        @CsvColumn(number=2, label="メールアドレス")     var email:String = "",
        @CsvColumn(number=3, label="役職")     var title:String = "",
        @CsvColumn(number=4, label="ダミー固定値") var dummy:String = "DUMMY",
        @CsvColumn(number=5, label="優先度")  var priority: String = ""
){
    constructor(sfCsv: SFExportCSV) : this(
            lastName = sfCsv.lastName + sfCsv.firstName,
            email = sfCsv.email,
            title = sfCsv.title,
            priority = sfCsv.level__c
            )
}