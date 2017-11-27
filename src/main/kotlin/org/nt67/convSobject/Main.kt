package org.nt67.convSobject

import com.github.mygreen.supercsv.io.LazyCsvAnnotationBeanReader
import org.supercsv.prefs.CsvPreference
import org.supercsv.quote.AlwaysQuoteMode
import java.io.BufferedReader
import java.nio.charset.Charset
import java.io.File
import java.nio.file.Files
import com.github.mygreen.supercsv.io.CsvAnnotationBeanWriter


class CsvReaderIterator<T>(val csvReader: LazyCsvAnnotationBeanReader<T>, private var current:T = csvReader.read()){
    operator fun next():T {
        val result = current
        current = csvReader.read()
        return result
    }
    operator fun hasNext(): Boolean = current != null
}
operator fun <T> LazyCsvAnnotationBeanReader<T>. iterator() = CsvReaderIterator(this)

fun main(args: Array<String>) {
    val reader = LazyCsvAnnotationBeanReader<SFExportCSV>(
            SFExportCSV::class.java,
            BufferedReader(SFExportCSV::class.java.getResourceAsStream("/sample/Contact_extract.csv").bufferedReader(Charset.forName("windows-31j"))),
            //Files.newBufferedReader(File("./sample/Contact_extract.csv").toPath(), Charset.forName("Windows-31j")),
            //CsvPreference.Builder(CsvPreference.STANDARD_PREFERENCE).useQuoteMode(AlwaysQuoteMode()).build()
            CsvPreference.STANDARD_PREFERENCE
    )
    reader.init()

    val writer = CsvAnnotationBeanWriter(
            OtherSystemCSV::class.java,
            Files.newBufferedWriter(File("otherSystem.csv").toPath(), Charset.forName("windows-31j")),
            //CsvPreference.STANDARD_PREFERENCE
            CsvPreference.Builder(CsvPreference.STANDARD_PREFERENCE).useQuoteMode(AlwaysQuoteMode()).build()
    )

    // ヘッダー行の書き込み
    writer.writeHeader()
    for(row in reader){
        println(row)
        writer.write(OtherSystemCSV(row))
    }
    writer.flush()
    writer.close()
}