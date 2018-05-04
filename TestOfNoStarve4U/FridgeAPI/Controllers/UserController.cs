using BusinessEntities;
using BusinessServices;
using ClassLibrary.BusinessServices;
using log4net;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace FridgeAPI.Controllers
{
    public class UserController : ApiController
    {
        IUserServices userServices = new UserServices();

        private static log4net.ILog Log { get; set; }

        ILog log = log4net.LogManager.GetLogger(typeof(ProductController));

        /// <summary>
        /// User.Add
        /// </summary>
        /// <param name="user"></param>
        /// <returns></returns>
        [HttpPost]
        public HttpResponseMessage Add(UserEntity user)
        {
            try
            {
                userServices.Add(user);

                return Request.CreateResponse(HttpStatusCode.OK);
            }
            catch (Exception e)
            {
                log.Error(e);

                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        /// <summary>
        /// User.Update
        /// </summary>
        /// <param name="user"></param>
        /// <returns></returns>
        [HttpPut]
        public HttpResponseMessage Update(UserEntity user)
        {
            try
            {
                userServices.Update(user);

                return Request.CreateResponse(HttpStatusCode.OK, user);
            }
            catch (Exception e)
            {
                log.Error(e);

                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        /// <summary>
        /// User.Delete
        /// </summary>
        /// <param name="id"></param>
        [HttpDelete]
        // s[Route("api/product/delete/{id}")]
        public void Delete(int id)
        {
            try
            {
                userServices.Delete(id);

                //return (Request.CreateResponse(HttpStatusCode.OK, "deleted"));
            }
            catch (Exception e)
            {
                log.Error(e);

                //return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        /// <summary>
        /// User.Get
        /// </summary>
        /// <param name="id"></param>
        /// <returns></returns>
        [HttpGet]
        public HttpResponseMessage Get(int id)
        {
            try
            {
                UserEntity user = userServices.Get(id);

                return (Request.CreateResponse(HttpStatusCode.OK, user));
            }
            catch (Exception e)
            {
                log.Error(e);

                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        /// <summary>
        /// User.GetList
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        public HttpResponseMessage GetList()
        {
            try
            {
                var userList = userServices.GetList();

                return Request.CreateResponse(HttpStatusCode.OK, userList);
            }
            catch (Exception e)
            {
                log.Error(e);

                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }
    }
}
