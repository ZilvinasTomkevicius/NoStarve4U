using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;


namespace FridgeAPI.Controllers.Models
{
    public class MyModelEntity
    {

        [Required]
        string Name { set; get; }
    }
}