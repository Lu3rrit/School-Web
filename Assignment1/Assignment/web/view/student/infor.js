   function deleteStudent(id)
            {
                var result = confirm("Are you sure?");
                if(result)
                {
                    window.location.href = 'delete?id='+ id;
                }
            }
