function printEpisodes(table) {
    var res = ""
    let children = table.getElementsByClassName("vevent")
    for (i = 0; i < children.length; i++) {
        try {
            // res += table.childNodes[i].childNodes[2].innerText.replace(/"/ig, "") + "\n"
            let name = children[i].childNodes[2].innerText.replace(/"/ig, "")
            res += name + "\n"
            if (name.indexOf('‡') >= 0) res += name + "\n"
        } catch (e) {
            console.error(i)
            console.error(e)
            console.log(table[i])
        }
    }
    return res;
}
