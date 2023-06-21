document.querySelectorAll(".createForm").forEach(form => {
    form.querySelector("button").addEventListener("click", async e => {
        e.preventDefault()
        const name = await form.querySelector(".name").value.split(" ")
        const data = await new FormData(form)
        console.log(data)
        console.log(name)
        if(await name.length > 1) {
            await data.append("firstName", name[0])
            await data.append("lastName", name[1])
        } else {
            await data.append("firstName", name[0])
            await data.append("lastName", "")
        }
        const req = await fetch("/api/record/create", {
            method: "POST",
            body: data
        })
        const res = await req.status
        if (res == 200) {
            form.querySelectorAll('input').forEach(input => {
                input.value = ""
            })
            form.querySelector("textarea").value = ""
        } else {
            alert("что-то пошло не так")
        }
    })
})

