/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function xeploai(chart, k,ch,h,ht,xs,text) {
    var xValues = ["khác", "chưa hoàn thành", "hoàn thành", "hoàn thành tốt", "xuất sắc"];
    var yValues = [k,ch,h,ht,xs];
    var barColors = [
        "#b91d47",
        "#00aba9",
        "#2b5797",
        "#e8c3b9",
        "#1e7145"
    ];

    new Chart(chart, {
        type: "pie",
        data: {
            labels: xValues,
            datasets: [{
                    backgroundColor: barColors,
                    data: yValues
                }]
        },
        options: {
            title: {
                display: true,
                text: text
            }
        }
    });
}



